package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.MateriaRepository;
import com.fmt.educafloripa.service.MateriaService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new MateriaResponse(entity.getId(), entity.getNome(), entity.getCurso().getId());
    }

    @Override
    protected MateriaEntity paraEntity(MateriaRequest requestDto) {
        CursoEntity curso = cursoService.pegarEntityPorId(requestDto.curso());
        return new MateriaEntity(requestDto, curso);
    }

    @Override
    public List<MateriaResponse> pegarMateriaPorCurso(Long idCurso) {
        CursoEntity curso = cursoService.pegarEntityPorId(idCurso);

        return repository.findAllByCurso(curso).stream().map(this::paraDto).toList();
    }
}
