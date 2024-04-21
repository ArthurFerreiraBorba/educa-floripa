package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.controller.dto.response.AlunoResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.AlunoService;
import com.fmt.educafloripa.service.NotaAlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

@RestController
@RequestMapping ("alunos")
public class AlunoController extends GenericController<AlunoService, AlunoResponse, AlunoRequest> {

    private final NotaAlunoService notaAlunoService;

    public AlunoController(NotaAlunoService notaAlunoService) {
        super(Arrays.asList(2L));
        this.notaAlunoService = notaAlunoService;
    }

    @GetMapping("pontuacao")
    public ResponseEntity<Float> buscarPontuacaoTotal(@RequestHeader(name = "Authorization") String token) {
        nivelAcesso(token, Arrays.asList(1L, 5L));
        return ResponseEntity.status(200).body(notaAlunoService.pegarPontuacaoTotal(token));
    }
}
