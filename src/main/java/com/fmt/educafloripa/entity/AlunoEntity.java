package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@Table(name = "alunos")
public class AlunoEntity extends GenericEntity {

    private String nome;

    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private TurmaEntity turma;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public AlunoEntity() {}

    public AlunoEntity(AlunoRequest request, TurmaEntity turma, UsuarioEntity usuario) {
        this.nome = request.nome();
        this.dataNascimento = request.dataNascimento();
        this.turma = turma;
        this.usuario = usuario;
    }
}
