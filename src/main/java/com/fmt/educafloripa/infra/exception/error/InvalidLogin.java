package com.fmt.educafloripa.infra.exception.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidLogin extends Invalid{
    public InvalidLogin(String login) {
        super("Nenhum usuário com esse login " + login + " encontrado");
        log.error("usuário com login {} é invalido", login);
    }
}
