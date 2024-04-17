package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.CadastroRequest;
import com.fmt.educafloripa.controller.dto.request.LoginRequest;
import com.fmt.educafloripa.controller.dto.response.CadastroResponse;
import com.fmt.educafloripa.controller.dto.response.LoginResponse;
import com.fmt.educafloripa.entity.PapelEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.repository.PapelRepository;
import com.fmt.educafloripa.repository.UsuarioRepository;
import com.fmt.educafloripa.service.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;

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

        Optional<PapelEntity> papelOp = eNumero(cadastroRequest.papel()) ?
                papelRepository.findById(Long.parseLong(cadastroRequest.papel())) :
                papelRepository.findByNome(cadastroRequest.papel().toUpperCase());

        if (papelOp.isEmpty()) {
            throw new RuntimeException("Papel não encontrado");
        }

        PapelEntity papel = papelOp.get();
        String senha = bCryptPasswordEncoder.encode(cadastroRequest.senha());

        UsuarioEntity usuario = new UsuarioEntity(cadastroRequest.login(), senha, papel);

        repository.save(usuario);
        return toDto(usuario);
    }

    @Override
    public LoginResponse criarToken(LoginRequest loginRequest) {
        UsuarioEntity usuario = repository.findByLogin(loginRequest.login()).orElseThrow(() -> new RuntimeException("Nenhum usuário com esse login encontrado"));

        if (!validarSenha(loginRequest, usuario)) {
            throw new RuntimeException("Senha invalida");
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

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO);
    }

    private CadastroResponse toDto(UsuarioEntity usuario) {
        return new CadastroResponse(usuario.getId(), usuario.getLogin(), usuario.getPapel().getNome());
    }

    private boolean validarSenha(LoginRequest loginrequest, UsuarioEntity usuario) {
        return bCryptPasswordEncoder.matches(loginrequest.senha(), usuario.getSenha());
    }

    private boolean eNumero(String texto) {
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
