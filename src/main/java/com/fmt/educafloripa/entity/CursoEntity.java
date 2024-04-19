package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.controller.dto.request.CursoRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name = "cursos")
public class CursoEntity extends GenericEntity {

    private String nome;

    public CursoEntity () {}

    public CursoEntity (CursoRequest request) {
        this.nome = request.nome();
    }
}
