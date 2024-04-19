package com.fmt.educafloripa.infra.generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<S extends GenericServiceInterface<RES, REQ>, RES, REQ> {

    @Autowired
    private S service;

    @GetMapping("buscar/{id}")
    public ResponseEntity<RES> buscarId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.pegarPorId(id));
    }

    @GetMapping("buscar")
    public ResponseEntity<List<RES>> buscarTodas() {
        return ResponseEntity.status(200).body(service.pegarTodos());
    }

    @PostMapping("criar")
    public ResponseEntity<RES> criar(@RequestBody REQ requestDto) {
        return ResponseEntity.status(201).body(service.criar(requestDto));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object> deleter(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizar(@RequestBody REQ requestDto, @PathVariable Long id) {
        service.atualizar(requestDto, id);
        return ResponseEntity.status(204).build();
    }


}
