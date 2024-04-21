package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.NotaAlunoService;
import com.fmt.educafloripa.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

@RestController
@RequestMapping ("notas")
public class NotaController extends GenericController<NotaService, NotaResponse, NotaRequest> {

    private final NotaAlunoService notaAlunoService;
    private final NotaService notaService;

    public NotaController(NotaAlunoService notaAlunoService, NotaService notaService) {
        super(Arrays.asList(1L, 4L));
        this.notaAlunoService = notaAlunoService;
        this.notaService = notaService;
    }

    @GetMapping("buscar/aluno/{idAluno}")
    public ResponseEntity<List<NotaResponse>> buscarNotaPorAluno(@RequestHeader(name = "Authorization") String token, @PathVariable Long idAluno) {
        nivelAcesso(token, Arrays.asList(1L, 4L));
        return ResponseEntity.status(200).body(notaAlunoService.pegarNotaPorAluno(idAluno));
    }

    @Override
    public ResponseEntity<NotaResponse> criar(String token, NotaRequest requestDto) {
        nivelAcesso(token, Arrays.asList(1L, 4L));
        return ResponseEntity.status(201).body(notaService.criar(requestDto, token));
    }
}
