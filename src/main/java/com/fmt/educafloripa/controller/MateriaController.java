package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.MateriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

@Slf4j
@RestController
@RequestMapping ("materis")
public class MateriaController extends GenericController<MateriaService, MateriaResponse, MateriaRequest> {

    private final MateriaService service;

    public MateriaController(MateriaService service) {
        super((Arrays.asList(2L)));
        this.service = service;
    }

    @GetMapping("buscar/curso/{idCurso}")
    public ResponseEntity<List<MateriaResponse>> buscarMateriaPorCurso(@RequestHeader(name = "Authorization") String token, @PathVariable Long idCurso) {
        nivelAcesso(token, Arrays.asList(4L));

        log.info("buscando matéria por curso com id {}", idCurso);

        return ResponseEntity.status(200).body(service.pegarMateriaPorCurso(idCurso));
    }
}
