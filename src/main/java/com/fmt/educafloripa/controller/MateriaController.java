package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.MateriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("materis")
public class MateriaController extends GenericController<MateriaService, MateriaResponse, MateriaRequest> {

    private final MateriaService service;

    public MateriaController(MateriaService service) {
        this.service = service;
    }

    @GetMapping("buscar/curso/{idCurso}")
    public ResponseEntity<List<MateriaResponse>> buscarMateriaPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.status(200).body(service.pegarMateriaPorCurso(idCurso));
    }
}
