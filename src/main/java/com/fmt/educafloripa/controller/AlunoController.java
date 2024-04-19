package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.controller.dto.response.AlunoResponse;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.AlunoService;
import com.fmt.educafloripa.service.NotaAlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("alunos")
public class AlunoController extends GenericController<AlunoService, AlunoResponse, AlunoRequest> {


    private final NotaAlunoService notaAlunoService;

    public AlunoController(NotaAlunoService notaAlunoService) {
        this.notaAlunoService = notaAlunoService;
    }

    @GetMapping("{id}/pontuacao")
    public ResponseEntity<Float> buscarNotaPorAluno(@PathVariable Long id) {
        return ResponseEntity.status(200).body(notaAlunoService.pegarPontuacaoTotal(id));
    }
}
