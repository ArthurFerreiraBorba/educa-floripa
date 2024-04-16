package com.fmt.educafloripa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "notas")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valor;

    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity professor;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;
}
