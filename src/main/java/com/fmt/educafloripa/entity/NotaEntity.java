package com.fmt.educafloripa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.infra.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import static com.fmt.educafloripa.infra.Util.NumeroUtil.adequarFloat;

@Data
@Entity
@DynamicInsert
@Table(name = "notas")
public class NotaEntity extends GenericEntity {

    private Float valor;

    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity professor;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;

    public NotaEntity () {}

    public NotaEntity (NotaRequest request, AlunoEntity aluno, DocenteEntity professor, MateriaEntity materia) {
        this.valor = adequarFloat(request.valor());
        this.aluno = aluno;
        this.professor = professor;
        this.materia = materia;
    }
}
