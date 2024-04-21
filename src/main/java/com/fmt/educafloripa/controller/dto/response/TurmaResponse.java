package com.fmt.educafloripa.controller.dto.response;

import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;

public record TurmaResponse(Long id, String nome, CursoEntity curso, DocenteEntity professor){
}
