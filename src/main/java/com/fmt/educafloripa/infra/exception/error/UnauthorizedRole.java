package com.fmt.educafloripa.infra.exception.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnauthorizedRole extends RuntimeException{

    public UnauthorizedRole() {
        super("você não possui autorização para acessar essa função");
        log.error("autorização negada");
    }
}
