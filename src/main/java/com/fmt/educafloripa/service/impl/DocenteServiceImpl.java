package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.controller.dto.response.DocenteResponse;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.exception.error.InvalidRole;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.DocenteRepository;
import com.fmt.educafloripa.repository.UsuarioRepository;
import com.fmt.educafloripa.service.DocenteService;
import com.fmt.educafloripa.service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DocenteServiceImpl extends GenericService<DocenteEntity, DocenteResponse, DocenteRequest> implements DocenteService {

    private final UsuarioServiceImpl usuarioService;
    private final DocenteRepository repository;

    public DocenteServiceImpl(DocenteRepository repository, UsuarioServiceImpl usuarioService) {
        super(repository);
        this.usuarioService = usuarioService;
        this.repository = repository;
    }

    @Override
    protected DocenteResponse paraDto(DocenteEntity entity) {
        return new DocenteResponse(entity.getId(), entity.getNome(), entity.getDataEntrada(), entity.getUsuario().getPapel().getNome());
    }

    @Override
    protected DocenteEntity paraEntity(DocenteRequest requestDto) {
        UsuarioEntity usuario = usuarioService.pegarEntityPorId(requestDto.usuario());

        if (usuario.getPapel().getId() == 5) {
            throw new InvalidRole("Usuários com papel de ALUNO não podem ser cadastrados como docente");
        }

        return new DocenteEntity(requestDto, usuario);
    }

    @Override
    public void atualizar (DocenteRequest requestDto, Long id) {
        DocenteEntity docenteDesatualizado =  pegarEntityPorId(id);
        DocenteEntity docenteAtualizado = paraEntity(requestDto);

        docenteAtualizado.setId(id);
        docenteAtualizado.setDataEntrada(docenteDesatualizado.getDataEntrada());

        repository.save(docenteAtualizado);
    }

    public DocenteEntity pegarPorIdUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioService.pegarEntityPorId(idUsuario);
        return repository.findByUsuario(usuario);
    }
}
