package com.fmt.educafloripa.infra.exception.error;

public class InvalidLogin extends Invalid{
    public InvalidLogin(String login) {
        super("Nenhum usuário com esse login " + login + " encontrado");
    }
}
