package com.fmt.educafloripa.infra.exception.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidPassword extends Invalid{

    public InvalidPassword (String senha, String login) {
        super("A senha " + senha + " é invalida");
        log.error("senha {} é invalida para o usuário com login {}", senha, login);
    }
}
