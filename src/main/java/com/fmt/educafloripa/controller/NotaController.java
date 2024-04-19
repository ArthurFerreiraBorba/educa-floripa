package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.NotaAlunoService;
import com.fmt.educafloripa.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("notas")
public class NotaController extends GenericController<NotaService, NotaResponse, NotaRequest> {

    private final NotaAlunoService notaAlunoService;

    public NotaController(NotaAlunoService notaAlunoService) {
        this.notaAlunoService = notaAlunoService;
    }

    @GetMapping("buscar/aluno/{idAluno}")
    public ResponseEntity<List<NotaResponse>> buscarNotaPorAluno(@PathVariable Long idAluno) {
        return ResponseEntity.status(200).body(notaAlunoService.pegarNotaPorAluno(idAluno));
    }
}
