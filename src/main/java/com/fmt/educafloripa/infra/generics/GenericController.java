package com.fmt.educafloripa.infra.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

public abstract class GenericController<S extends GenericServiceInterface<RES, REQ>, RES, REQ> {

    @Autowired
    private S service;
    private final List<Long> nivel;

    protected GenericController(List<Long> nivel) {
        this.nivel = nivel;
    }


    @GetMapping("buscar/{id}")
    public ResponseEntity<RES> buscarId(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        nivelAcesso(token, nivel);
        return ResponseEntity.status(200).body(service.pegarPorId(id));
    }

    @GetMapping("buscar")
    public ResponseEntity<List<RES>> buscarTodas(@RequestHeader(name = "Authorization") String token) {
        nivelAcesso(token, nivel);
        return ResponseEntity.status(200).body(service.pegarTodos());
    }

    @PostMapping("criar")
    public ResponseEntity<RES> criar(@RequestHeader(name = "Authorization") String token, @RequestBody REQ requestDto) {
        nivelAcesso(token, nivel);
        return ResponseEntity.status(201).body(service.criar(requestDto));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object> deleter(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        nivelAcesso(token, Arrays.asList(1L));
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizar(@RequestHeader(name = "Authorization") String token, @RequestBody REQ requestDto, @PathVariable Long id) {
        nivelAcesso(token, nivel);
        service.atualizar(requestDto, id);
        return ResponseEntity.status(204).build();
    }


}
