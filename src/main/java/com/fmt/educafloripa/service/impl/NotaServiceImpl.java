package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.entity.NotaEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.NotaRepository;
import com.fmt.educafloripa.service.NotaService;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl extends GenericService<NotaEntity, NotaResponse, NotaRequest> implements NotaService {

    private final DocenteServiceImpl docenteService;
    private final AlunoServiceImpl alunoService;
    private final MateriaServiceImpl materiaService;
    private final NotaRepository repository;
    public NotaServiceImpl(NotaRepository repository, DocenteServiceImpl docenteService, AlunoServiceImpl alunoService, MateriaServiceImpl materiaService) {
        super(repository);
        this.docenteService = docenteService;
        this.alunoService = alunoService;
        this.materiaService = materiaService;
        this.repository = repository;
    }

    @Override
    protected NotaResponse paraDto(NotaEntity entity) {
        return new NotaResponse(entity.getId(), entity.getValor(), entity.getDataCriacao(), entity.getAluno().getNome(), entity.getProfessor().getNome(), entity.getMateria().getNome());
    }

    @Override
    protected NotaEntity paraEntity(NotaRequest requestDto) {
        DocenteEntity professor = docenteService.pegarEntityPorId(requestDto.professor());
        AlunoEntity aluno = alunoService.pegarEntityPorId(requestDto.aluno());
        MateriaEntity materia = materiaService.pegarEntityPorId(requestDto.materia());
        return new NotaEntity(requestDto, aluno, professor, materia);
    }

    @Override
    public void atualizar (NotaRequest requestDto, Long id) {
        NotaEntity notaDesatualizado =  pegarEntityPorId(id);
        NotaEntity notaAtualizado = paraEntity(requestDto);

        notaAtualizado.setId(id);
        notaAtualizado.setDataCriacao(notaDesatualizado.getDataCriacao());

        repository.save(notaAtualizado);
    }
}
