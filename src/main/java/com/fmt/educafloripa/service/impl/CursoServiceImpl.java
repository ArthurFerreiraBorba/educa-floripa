package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.CursoRequest;
import com.fmt.educafloripa.controller.dto.response.CursoResponse;
import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.CursoRepository;
import com.fmt.educafloripa.service.CursoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CursoServiceImpl extends GenericService<CursoEntity, CursoResponse, CursoRequest> implements CursoService {

    public CursoServiceImpl(CursoRepository repository) {
        super(repository);
    }

    @Override
    protected CursoResponse paraDto(CursoEntity entity) {

        log.info("convertendo entidade de curso para dto");

        return new CursoResponse(entity.getId(), entity.getNome());
    }

    @Override
    protected CursoEntity paraEntity(CursoRequest requestDto) {

        log.info("convertendo dto de curso para entidade");

        return new CursoEntity(requestDto);
    }
}
