package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.NotaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("notas")
public class NotaController extends GenericController<NotaService, NotaResponse, NotaRequest> {
}
