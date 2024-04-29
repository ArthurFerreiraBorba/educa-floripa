package com.fmt.educafloripa.infra.exception.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException (String mensagem) {
        super(mensagem);
    }
}
