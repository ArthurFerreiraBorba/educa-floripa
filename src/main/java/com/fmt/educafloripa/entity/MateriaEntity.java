package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name = "materias")
public class MateriaEntity extends GenericEntity {

    private String nome;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    public MateriaEntity () {}

    public MateriaEntity (MateriaRequest request, CursoEntity curso) {
        this.nome = request.nome();
        this.curso = curso;
    }
}
