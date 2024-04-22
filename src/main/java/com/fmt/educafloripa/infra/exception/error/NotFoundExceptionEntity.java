package com.fmt.educafloripa.infra.exception.error;

public class NotFoundExceptionEntity extends NotFoundException{

    public NotFoundExceptionEntity(String entity, Long id) {
        super(entity + " com id " + id + " não encontrado");
    }

    public NotFoundExceptionEntity(String entity, String nome) {
        super(entity + " com nome " + nome + " não encontrado");
    }
}
