package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.AlunoRequest;
import com.fmt.educafloripa.controller.dto.response.AlunoResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.AlunoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("alunos")
public class AlunoController extends GenericController<AlunoService, AlunoResponse, AlunoRequest> {
}
