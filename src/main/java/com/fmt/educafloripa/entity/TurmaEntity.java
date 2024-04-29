package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.controller.dto.request.TurmaRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "turmas")
public class TurmaEntity extends GenericEntity {

    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity professor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    public TurmaEntity() {}

    public TurmaEntity(TurmaRequest requestDto, CursoEntity curso, DocenteEntity docente) {
        this.nome = requestDto.nome();
        this.professor = docente;
        this.curso = curso;
    }
}
