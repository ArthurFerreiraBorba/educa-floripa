package com.fmt.educafloripa.service;

import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.infra.generics.GenericServiceInterface;

public interface NotaService extends GenericServiceInterface<NotaResponse, NotaRequest> {

    NotaResponse criar (NotaRequest requestDto, String token);
}