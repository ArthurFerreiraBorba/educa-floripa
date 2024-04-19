package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.entity.NotaEntity;
import com.fmt.educafloripa.repository.NotaRepository;
import com.fmt.educafloripa.service.NotaAlunoService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotaAlunoServiceImpl implements NotaAlunoService {

    private final AlunoServiceImpl alunoService;

    private final NotaRepository notaRepository;

    private final NotaServiceImpl notaService;

    public NotaAlunoServiceImpl(AlunoServiceImpl alunoService, NotaRepository notaRepository, NotaServiceImpl notaService) {
        this.alunoService = alunoService;
        this.notaRepository = notaRepository;
        this.notaService = notaService;
    }

    @Override
    public Float pegarPontuacaoTotal(Long idAluno) {
        List<NotaEntity> notas = pegarNotaEntityPorAluno(idAluno);

        Set<MateriaEntity> materias = new HashSet<>();
        Float pontuacao = 0f;

        for (NotaEntity nota : notas) {
            pontuacao += nota.getValor();
            materias.add(nota.getMateria());
        }

        return pontuacao / materias.size() * 10;
    }

    @Override
    public List<NotaResponse> pegarNotaPorAluno(Long idAluno) {
        return pegarNotaEntityPorAluno(idAluno).stream().map(notaService::paraDto).toList();
    }

    public List<NotaEntity> pegarNotaEntityPorAluno(Long idAluno) {
        AlunoEntity aluno = alunoService.pegarEntityPorId(idAluno);

        return notaRepository.findAllByAluno(aluno);
    }
}
