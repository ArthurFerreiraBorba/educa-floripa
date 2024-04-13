package com.fmt.educafloripa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "papel_id")
    private PapelEntity papel;
}
