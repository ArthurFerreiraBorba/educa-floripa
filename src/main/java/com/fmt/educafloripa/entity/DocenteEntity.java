package com.fmt.educafloripa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "docentes")
public class DocenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
