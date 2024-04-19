package com.fmt.educafloripa.service;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.infra.generics.GenericServiceInterface;

import java.util.List;

public interface MateriaService extends GenericServiceInterface<MateriaResponse, MateriaRequest> {

    public List<MateriaResponse> pegarMateriaPorCurso(Long idCurso);
}