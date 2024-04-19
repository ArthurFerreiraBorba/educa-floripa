package com.fmt.educafloripa.controller.dto.response;

import java.time.LocalDate;

public record DocenteResponse(Long id,String nome, LocalDate dataEntrada, Long usuario){
}
