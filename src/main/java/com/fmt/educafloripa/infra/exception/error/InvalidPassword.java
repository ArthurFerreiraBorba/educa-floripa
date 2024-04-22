package com.fmt.educafloripa.infra.exception.error;

public class InvalidPassword extends Invalid{

    public InvalidPassword (String senha) {
        super("A senha " + senha + " é invalida");
    }
}
