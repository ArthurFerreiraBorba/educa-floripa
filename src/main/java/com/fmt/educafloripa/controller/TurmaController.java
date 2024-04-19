package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.TurmaRequest;
import com.fmt.educafloripa.controller.dto.response.TurmaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.TurmaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("turmas")
public class TurmaController extends GenericController<TurmaService, TurmaResponse, TurmaRequest> {
}
