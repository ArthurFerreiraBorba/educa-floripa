package com.fmt.educafloripa.controller.dto.response;

import com.fmt.educafloripa.entity.TurmaEntity;

import java.time.LocalDate;

public record AlunoResponse(Long id, String nome, LocalDate dataNascimento, String papel, TurmaEntity turma){
}
