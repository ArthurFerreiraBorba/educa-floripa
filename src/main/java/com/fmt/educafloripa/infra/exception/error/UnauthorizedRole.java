package com.fmt.educafloripa.infra.exception.error;

public class UnauthorizedRole extends RuntimeException{

    public UnauthorizedRole() {
        super("você não possui autorização para acessar essa função");
    }
}
