package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.TurmaRequest;
import com.fmt.educafloripa.controller.dto.response.TurmaResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.TurmaEntity;
import com.fmt.educafloripa.infra.exception.error.InvalidRole;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.TurmaRepository;
import com.fmt.educafloripa.service.TurmaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("convertendo entidade turma para dto");

        return new TurmaResponse(entity.getId(), entity.getNome(), entity.getCurso(), entity.getProfessor());
    }

    @Override
    protected TurmaEntity paraEntity(TurmaRequest requestDto) {

        log.info("convertendo dto turma para entidade");

        CursoEntity curso = cursoService.pegarEntityPorId(requestDto.curso());
        DocenteEntity docente = docenteService.pegarEntityPorId(requestDto.professor());

        if (docente.getUsuario().getPapel().getId() != 4) {
            log.error("O docente precisa possuir o papel de PROFESSOR. O docente informado é um {}", docente.getUsuario().getPapel().getNome());
            throw new InvalidRole("O docente precisa possuir o papel de PROFESSOR. O docente informado é um " + docente.getUsuario().getPapel().getNome());
        }

        return new TurmaEntity(requestDto, curso, docente);
    }
}
