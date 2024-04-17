package com.fmt.educafloripa.infra.generics;

import java.util.List;

public interface GenericServiceInterface<RES, REQ> {
    RES pegarPorId(Long id);

    List<RES> pegarTodos();

    RES criar (REQ requestDto);

    void deletar (Long id);

    void atualizar (REQ requestDto, Long id);
}
