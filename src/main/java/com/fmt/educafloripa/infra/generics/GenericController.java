package com.fmt.educafloripa.infra.generics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import static com.fmt.educafloripa.infra.Util.AcessoUtil.nivelAcesso;

@Slf4j
public abstract class GenericController<S extends GenericServiceInterface<RES, REQ>, RES, REQ> {

    @Autowired
    private S service;
    private final List<Long> nivel;
    private final String nomeEntity;

    protected GenericController(List<Long> nivel) {
        this.nivel = nivel;
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        this.nomeEntity = parameterizedType.getActualTypeArguments()[0].getTypeName().substring(29).replace("Service", "");
    }


    @GetMapping("buscar/{id}")
    public ResponseEntity<RES> buscarId(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        nivelAcesso(token, nivel);

        log.info("buscando {} com id {}", nomeEntity, id);

        return ResponseEntity.status(200).body(service.pegarPorId(id));
    }

    @GetMapping("buscar")
    public ResponseEntity<List<RES>> buscarTodas(@RequestHeader(name = "Authorization") String token) {
        nivelAcesso(token, nivel);

        log.info("buscando todas as entidades {}", nomeEntity);

        return ResponseEntity.status(200).body(service.pegarTodos());
    }

    @PostMapping("criar")
    public ResponseEntity<RES> criar(@RequestHeader(name = "Authorization") String token, @RequestBody REQ requestDto) {
        nivelAcesso(token, nivel);

        log.info("criando {}", nomeEntity);

        return ResponseEntity.status(201).body(service.criar(requestDto));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object> deleter(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        nivelAcesso(token, Arrays.asList(1L));

        log.info("deletando {} com id {}", nomeEntity, id);

        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizar(@RequestHeader(name = "Authorization") String token, @RequestBody REQ requestDto, @PathVariable Long id) {
        nivelAcesso(token, nivel);

        log.info("atualizando {} com id {}", nomeEntity, id);

        service.atualizar(requestDto, id);
        return ResponseEntity.status(204).build();
    }


}
