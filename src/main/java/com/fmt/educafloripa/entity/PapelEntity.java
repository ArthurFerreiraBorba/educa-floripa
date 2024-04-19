package com.fmt.educafloripa.entity;

import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name = "papeis")
public class PapelEntity extends GenericEntity {

    private String nome;

}
