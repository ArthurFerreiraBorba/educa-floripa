package com.fmt.educafloripa.controller;

import com.fmt.educafloripa.controller.dto.request.MateriaRequest;
import com.fmt.educafloripa.controller.dto.response.MateriaResponse;
import com.fmt.educafloripa.infra.generics.GenericController;
import com.fmt.educafloripa.service.MateriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("materis")
public class MateriaController extends GenericController<MateriaService, MateriaResponse, MateriaRequest> {
}
