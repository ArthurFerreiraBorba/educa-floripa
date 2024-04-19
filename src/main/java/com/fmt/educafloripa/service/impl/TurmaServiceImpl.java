package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.TurmaRequest;
import com.fmt.educafloripa.controller.dto.response.TurmaResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.TurmaEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.TurmaRepository;
import com.fmt.educafloripa.service.TurmaService;
import org.springframework.stereotype.Service;

@Service
public class TurmaServiceImpl extends GenericService<TurmaEntity, TurmaResponse, TurmaRequest> implements TurmaService {

    public final CursoServiceImpl cursoService;
    public final DocenteServiceImpl docenteService;

    public TurmaServiceImpl(TurmaRepository repository, CursoServiceImpl cursoService, DocenteServiceImpl docenteService) {
        super(repository);
        this.cursoService = cursoService;
        this.docenteService = docenteService;
    }

    @Override
    protected TurmaResponse paraDto(TurmaEntity entity) {
        return new TurmaResponse(entity.getId(), entity.getNome(), entity.getCurso().getId(), entity.getProfessor().getId());
    }

    @Override
    protected TurmaEntity paraEntity(TurmaRequest requestDto) {
        CursoEntity curso = cursoService.pegarEntityPorId(requestDto.curso());
        DocenteEntity docente = docenteService.pegarEntityPorId(requestDto.professor());
        return new TurmaEntity(requestDto, curso, docente);
    }
}
