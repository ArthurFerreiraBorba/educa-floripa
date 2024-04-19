package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@Table(name = "docentes")
public class DocenteEntity extends GenericEntity {

    private String nome;

    private LocalDate dataEntrada;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public DocenteEntity (){}

    public DocenteEntity (DocenteRequest request, UsuarioEntity usuario){
        this.nome = request.nome();
        this.usuario = usuario;
    }

}
