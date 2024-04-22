package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.CadastroRequest;
import com.fmt.educafloripa.controller.dto.request.LoginRequest;
import com.fmt.educafloripa.controller.dto.response.CadastroResponse;
import com.fmt.educafloripa.controller.dto.response.LoginResponse;
import com.fmt.educafloripa.entity.PapelEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.exception.error.InvalidLogin;
import com.fmt.educafloripa.infra.exception.error.InvalidPassword;
import com.fmt.educafloripa.infra.exception.error.NotFoundExceptionEntity;
import com.fmt.educafloripa.repository.PapelRepository;
import com.fmt.educafloripa.repository.UsuarioRepository;
import com.fmt.educafloripa.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;

import static com.fmt.educafloripa.infra.Util.NumeroUtil.eNumero;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PapelRepository papelRepository;
    private final JwtEncoder jwtEncoder;
    private static long TEMPO_EXPIRACAO = 36000L;

    public UsuarioServiceImpl(UsuarioRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, PapelRepository papelRepository, JwtEncoder jwtEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.papelRepository = papelRepository;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public CadastroResponse cadastrarUsuario(CadastroRequest cadastroRequest) {

        repository.findByLogin(cadastroRequest.login()).ifPresent(usuario -> {throw new RuntimeException("Usuário já existe");});

        PapelEntity papel;

        if (eNumero(cadastroRequest.papel())) {
            papel = papelRepository.findById(Long.parseLong(cadastroRequest.papel())).orElseThrow(() -> new NotFoundExceptionEntity(PapelEntity.class.getSimpleName().replace("Entity", ""), Long.parseLong(cadastroRequest.papel())));
        } else {
            papel = papelRepository.findByNome(cadastroRequest.papel().toUpperCase()).orElseThrow(() -> new NotFoundExceptionEntity(PapelEntity.class.getSimpleName().replace("Entity", ""), cadastroRequest.papel()));
        }

        String senha = bCryptPasswordEncoder.encode(cadastroRequest.senha());

        UsuarioEntity usuario = new UsuarioEntity(cadastroRequest.login(), senha, papel);

        repository.save(usuario);

        log.info("entidade usuário criada com sucesso");

        return toDto(usuario);
    }

    @Override
    public LoginResponse criarToken(LoginRequest loginRequest) {
        UsuarioEntity usuario = repository.findByLogin(loginRequest.login()).orElseThrow(() -> new InvalidLogin(loginRequest.login()));

        if (!validarSenha(loginRequest, usuario)) {
            throw new InvalidPassword(loginRequest.senha(), loginRequest.login());
        }

        Instant now = Instant.now();

        String scope = usuario.getPapel().getNome();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("educa floripa")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO))
                .subject(usuario.getId().toString())
                .claim("scope", scope)
                .build();

        var valorJWT = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.info("token criada com sucesso");

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO);
    }

    private CadastroResponse toDto(UsuarioEntity usuario) {

        log.info("convertendo entidade usuário para dto");

        return new CadastroResponse(usuario.getId(), usuario.getLogin(), usuario.getPapel().getNome());
    }

    private boolean validarSenha(LoginRequest loginrequest, UsuarioEntity usuario) {

        log.info("validando senha");

        return bCryptPasswordEncoder.matches(loginrequest.senha(), usuario.getSenha());
    }

    public UsuarioEntity pegarEntityPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
