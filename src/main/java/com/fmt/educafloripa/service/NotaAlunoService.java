package com.fmt.educafloripa.service;

import com.fmt.educafloripa.controller.dto.response.NotaResponse;

import java.util.List;

public interface NotaAlunoService {

    Float pegarPontuacaoTotal(Long id);

    List<NotaResponse> pegarNotaPorAluno(Long idAluno);
}