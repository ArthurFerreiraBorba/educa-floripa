package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.controller.dto.response.AlunoResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.TurmaEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.AlunoRepository;
import com.fmt.educafloripa.service.AlunoService;
import org.springframework.stereotype.Service;

@Service
public class AlunoServiceImpl extends GenericService<AlunoEntity, AlunoResponse, AlunoRequest> implements AlunoService {

    private final UsuarioServiceImpl usuarioService;

    private final TurmaServiceImpl turmaService;

    public AlunoServiceImpl(AlunoRepository repository, UsuarioServiceImpl usuarioService, TurmaServiceImpl turmaService) {
        super(repository);
        this.usuarioService = usuarioService;
        this.turmaService = turmaService;
    }

    @Override
    protected AlunoResponse paraDto(AlunoEntity entity) {
        return new AlunoResponse(entity.getId(), entity.getNome(), entity.getDataNascimento(), entity.getTurma().getNome(), entity.getUsuario().getPapel().getNome());
    }

    @Override
    protected AlunoEntity paraEntity(AlunoRequest requestDto) {
        UsuarioEntity usuario = usuarioService.pegarEntityPorId(requestDto.usuario());
        TurmaEntity turma = turmaService.pegarEntityPorId(requestDto.turma());
        return new AlunoEntity(requestDto, turma, usuario);
    }
}
