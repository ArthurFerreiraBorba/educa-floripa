package com.fmt.educafloripa.infra.generics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<E extends GenericEntity, RES, REQ> implements GenericServiceInterface<RES, REQ>{

    private final JpaRepository<E, Long> repository;

    protected GenericService(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    protected abstract RES paraDto(E entity);

    protected abstract E paraEntity(REQ requestDto);

    public RES pegarPorId(Long id) {
        entidadeExiste(id);

        return paraDto(repository.findById(id).get());
    }

    public E pegarEntityPorId(Long id) {
        entidadeExiste(id);
        return repository.findById(id).get();
    }

    public List<RES> pegarTodos() {
        return repository.findAll().stream().map(this::paraDto).toList();
    }

    public RES criar (REQ requestDto) {
        E entity = paraEntity(requestDto);
        entity.setId(null);

        repository.save(entity);

        return paraDto(entity);
    }

    public void deletar (Long id){
        entidadeExiste(id);
        repository.deleteById(id);
    }

    public void atualizar (REQ requestDto, Long id) {
        entidadeExiste(id);
        E entity = paraEntity(requestDto);

        entity.setId(id);

        repository.save(entity);
    }

    public void entidadeExiste(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException();
        }
    }
}
