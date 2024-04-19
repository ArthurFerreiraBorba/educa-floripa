package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.CursoRequest;
import com.fmt.educafloripa.controller.dto.response.CursoResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.CursoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("cursos")
public class CursoController extends GenericController<CursoService, CursoResponse, CursoRequest> {
}
