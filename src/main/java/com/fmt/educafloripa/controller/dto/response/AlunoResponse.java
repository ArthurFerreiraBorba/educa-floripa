package com.fmt.educafloripa.controller.dto.response;

import java.time.LocalDate;

public record AlunoResponse(Long id, String nome, LocalDate dataNascimento, String turma, String papel){
}
