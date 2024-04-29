package com.fmt.educafloripa.controller.dto.response;

import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.MateriaEntity;

import java.time.LocalDate;

public record NotaResponse(Long id, Float valor, LocalDate dataCricao, String aluno, DocenteEntity professor, MateriaEntity materia){
}
