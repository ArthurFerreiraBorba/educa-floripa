package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.controller.dto.response.DocenteResponse;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.DocenteRepository;
import com.fmt.educafloripa.repository.UsuarioRepository;
import com.fmt.educafloripa.service.DocenteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DocenteServiceImpl extends GenericService<DocenteEntity, DocenteResponse, DocenteRequest> implements DocenteService {

    private final UsuarioRepository usuarioRepository;

    public DocenteServiceImpl(DocenteRepository repository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected DocenteResponse paraDto(DocenteEntity entity) {
        return new DocenteResponse(entity.getId(), entity.getNome(), entity.getDataEntrada(), entity.getId());
    }

    @Override
    protected DocenteEntity paraEntity(DocenteRequest requestDto) {
        UsuarioEntity usuario = usuarioRepository.findById(requestDto.usuario()).orElseThrow();
        return new DocenteEntity(requestDto, usuario);
    }
}
