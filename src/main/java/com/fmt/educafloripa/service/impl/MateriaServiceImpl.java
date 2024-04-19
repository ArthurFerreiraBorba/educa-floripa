package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.MateriaRepository;
import com.fmt.educafloripa.service.CursoService;
import com.fmt.educafloripa.service.MateriaService;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImpl extends GenericService<MateriaEntity, MateriaResponse, MateriaRequest> implements MateriaService {

    public final CursoServiceImpl cursoService;

    public MateriaServiceImpl(MateriaRepository repository, CursoServiceImpl cursoService) {
        super(repository);
        this.cursoService = cursoService;
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
}
