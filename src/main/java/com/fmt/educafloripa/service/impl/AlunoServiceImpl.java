package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.controller.dto.response.AlunoResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.TurmaEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import com.fmt.educafloripa.infra.exception.error.InvalidRole;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.AlunoRepository;
import com.fmt.educafloripa.service.AlunoService;
import org.springframework.stereotype.Service;

@Service
public class AlunoServiceImpl extends GenericService<AlunoEntity, AlunoResponse, AlunoRequest> implements AlunoService {

    private final AlunoRepository repository;
    private final UsuarioServiceImpl usuarioService;

    private final TurmaServiceImpl turmaService;

    public AlunoServiceImpl(AlunoRepository repository, UsuarioServiceImpl usuarioService, TurmaServiceImpl turmaService) {
        super(repository);
        this.usuarioService = usuarioService;
        this.turmaService = turmaService;
        this.repository = repository;
    }

    @Override
    protected AlunoResponse paraDto(AlunoEntity entity) {
        return new AlunoResponse(entity.getId(), entity.getNome(), entity.getDataNascimento(), entity.getUsuario().getPapel().getNome(), entity.getTurma());
    }

    @Override
    protected AlunoEntity paraEntity(AlunoRequest requestDto) {

        UsuarioEntity usuario = usuarioService.pegarEntityPorId(requestDto.usuario());
        TurmaEntity turma = turmaService.pegarEntityPorId(requestDto.turma());

        if (usuario.getPapel().getId() != 5) {
            throw new InvalidRole("Somente usuários com o papel de ALUNO podem ser cadastrados como alunos");
        }

        return new AlunoEntity(requestDto, turma, usuario);
    }

    public AlunoEntity pegarPorIdUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioService.pegarEntityPorId(idUsuario);
        return repository.findByUsuario(usuario);
    }
}
