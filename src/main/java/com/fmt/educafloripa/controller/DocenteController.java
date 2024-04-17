package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.controller.dto.response.DocenteResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.DocenteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("docentes")
public class DocenteController extends GenericController<DocenteService, DocenteResponse, DocenteRequest> {
}
