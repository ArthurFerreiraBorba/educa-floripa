package com.fmt.educafloripa.controller.dto.response;

import com.fmt.educafloripa.entity.CursoEntity;

public record MateriaResponse(Long id, String nome, CursoEntity curso){
}
