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
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl extends GenericService<NotaEntity, NotaResponse, NotaRequest> implements NotaService {

    private final DocenteServiceImpl docenteService;
    private final AlunoServiceImpl alunoService;
    private final MateriaServiceImpl materiaService;
    private final NotaRepository repository;
    private final JwtDecoder jwtDecoder;
    public NotaServiceImpl(NotaRepository repository, DocenteServiceImpl docenteService, AlunoServiceImpl alunoService, MateriaServiceImpl materiaService, JwtDecoder jwtDecoder) {
        super(repository);
        this.docenteService = docenteService;
        this.alunoService = alunoService;
        this.materiaService = materiaService;
        this.repository = repository;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    protected NotaResponse paraDto(NotaEntity entity) {
        return new NotaResponse(entity.getId(), entity.getValor(), entity.getDataCriacao(), entity.getAluno().getNome(), entity.getProfessor(), entity.getMateria());
    }

    @Override
    protected NotaEntity paraEntity(NotaRequest requestDto) {
        return new NotaEntity();
    }

    @Override
    public void atualizar (NotaRequest requestDto, Long id) {
        NotaEntity notaDesatualizado =  pegarEntityPorId(id);

        DocenteEntity professor = notaDesatualizado.getProfessor();
        AlunoEntity aluno = alunoService.pegarEntityPorId(requestDto.aluno());
        MateriaEntity materia = materiaService.pegarEntityPorId(requestDto.materia());

        if (aluno.getTurma().getProfessor() != professor) {
            throw new RuntimeException();
        }
        if (aluno.getTurma().getCurso() != materia.getCurso()) {
            throw new RuntimeException();
        }

        NotaEntity notaAtualizado = new NotaEntity(requestDto, aluno, professor, materia);

        notaAtualizado.setId(id);
        notaAtualizado.setDataCriacao(notaDesatualizado.getDataCriacao());

        repository.save(notaAtualizado);
    }

    public NotaResponse criar (NotaRequest requestDto, String token) {

        token = token.substring(7);
        Long idUsuario = Long.valueOf(
                jwtDecoder.decode(token).getClaims().get("sub").toString()
        );

        DocenteEntity professor = docenteService.pegarPorIdUsuario(idUsuario);
        AlunoEntity aluno = alunoService.pegarEntityPorId(requestDto.aluno());
        MateriaEntity materia = materiaService.pegarEntityPorId(requestDto.materia());

        if (aluno.getTurma().getProfessor() != professor) {
            throw new RuntimeException();
        }
        if (aluno.getTurma().getCurso() != materia.getCurso()) {
            throw new RuntimeException();
        }

        NotaEntity entity = new NotaEntity(requestDto, aluno, professor, materia);

        repository.save(entity);

        return paraDto(entity);
    }

}
