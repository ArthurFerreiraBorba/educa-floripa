package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.entity.NotaEntity;
import com.fmt.educafloripa.repository.NotaRepository;
import com.fmt.educafloripa.service.NotaAlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class NotaAlunoServiceImpl implements NotaAlunoService {

    private final JwtDecoder jwtDecoder;

    private final AlunoServiceImpl alunoService;

    private final NotaRepository notaRepository;

    private final NotaServiceImpl notaService;

    public NotaAlunoServiceImpl(JwtDecoder jwtDecoder, AlunoServiceImpl alunoService, NotaRepository notaRepository, NotaServiceImpl notaService) {
        this.jwtDecoder = jwtDecoder;
        this.alunoService = alunoService;
        this.notaRepository = notaRepository;
        this.notaService = notaService;
    }

    @Override
    public Float pegarPontuacaoTotal(String token) {

        token = token.substring(7);
        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        log.info("buscando notas de aluno");

        List<NotaEntity> notas = pegarNotaEntityPorAluno(idUsuario);

        log.info("calculando pontuação");

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
        AlunoEntity aluno = alunoService.pegarEntityPorId(idAluno);
        return notaRepository.findAllByAluno(aluno).stream().map(notaService::paraDto).toList();
    }

    private List<NotaEntity> pegarNotaEntityPorAluno(Long idUsuario) {
        AlunoEntity aluno = alunoService.pegarPorIdUsuario(idUsuario);

        return notaRepository.findAllByAluno(aluno);
    }
}
