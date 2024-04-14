package com.fmt.educafloripa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "papeis")
public class PapelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

}
