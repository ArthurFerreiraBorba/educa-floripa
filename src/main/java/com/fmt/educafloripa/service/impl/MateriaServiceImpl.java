package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.MateriaRepository;
import com.fmt.educafloripa.service.MateriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MateriaServiceImpl extends GenericService<MateriaEntity, MateriaResponse, MateriaRequest> implements MateriaService {

    public final CursoServiceImpl cursoService;
    public final MateriaRepository repository;

    public MateriaServiceImpl(MateriaRepository repository, CursoServiceImpl cursoService) {
        super(repository);
        this.cursoService = cursoService;
        this.repository = repository;
    }

    @Override
    protected MateriaResponse paraDto(MateriaEntity entity) {

        log.info("convertendo entidade de matéria para dto");

        return new MateriaResponse(entity.getId(), entity.getNome(), entity.getCurso());
    }

    @Override
    protected MateriaEntity paraEntity(MateriaRequest requestDto) {

        log.info("convertendo dto de matéria para entidade");

        CursoEntity curso = cursoService.pegarEntityPorId(requestDto.curso());
        return new MateriaEntity(requestDto, curso);
    }

    @Override
    public List<MateriaResponse> pegarMateriaPorCurso(Long idCurso) {
        CursoEntity curso = cursoService.pegarEntityPorId(idCurso);

        return repository.findAllByCurso(curso).stream().map(this::paraDto).toList();
    }
}
