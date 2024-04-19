package com.fmt.educafloripa.controller.dto.response;

import java.time.LocalDate;

public record NotaResponse(Long id, Float valor, LocalDate dataCricao, String aluno, String professor, String materia){
}
