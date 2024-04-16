package com.fmt.educafloripa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "alunos")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private TurmaEntity turma;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
