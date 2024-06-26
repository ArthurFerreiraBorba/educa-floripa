package com.fmt.educafloripa.infra.Util;

import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.ApplicationContext.ApplicationContextProvider;
import com.fmt.educafloripa.infra.exception.error.UnauthorizedRole;
import com.fmt.educafloripa.service.impl.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.util.List;

@Slf4j
public class AcessoUtil {

    public static void nivelAcesso(String token, List<Long> niveis) {

        log.info("verificando nível de acesso");

        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        JwtDecoder jwtDecoder = context.getBean(JwtDecoder.class);
        UsuarioServiceImpl usuarioService = context.getBean(UsuarioServiceImpl.class);

        if (niveis.size() == 1) {
            nivelAcesso(token, niveis.get(0), jwtDecoder, usuarioService);
            log.info("Acesso autorizado");
            return;
        }

        token = token.substring(7);
        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        UsuarioEntity usuario = usuarioService.pegarEntityPorId(idUsuario);

        for (Long nivel : niveis) {
            if (usuario.getPapel().getId().equals(nivel)) {
                log.info("Acesso autorizado");
                return;
            }
        }

        throw new UnauthorizedRole();
    }

    private static void nivelAcesso(String token, Long nivel, JwtDecoder jwtDecoder, UsuarioServiceImpl usuarioService) {

        token = token.substring(7);
        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        UsuarioEntity usuario = usuarioService.pegarEntityPorId(idUsuario);

        if (!(usuario.getPapel().getId() > nivel)) {
            return;
        }

        throw new UnauthorizedRole();
    }
}
