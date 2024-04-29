package com.fmt.educafloripa.controller.dto.request;

import java.time.LocalDate;

public record AlunoRequest(String nome, LocalDate dataNascimento, Long turma, Long usuario){
}
