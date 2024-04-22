package com.fmt.educafloripa.service.impl;

import com.fmt.educafloripa.controller.dto.request.DocenteRequest;
import com.fmt.educafloripa.controller.dto.request.NotaRequest;
import com.fmt.educafloripa.controller.dto.response.NotaResponse;
import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import com.fmt.educafloripa.entity.NotaEntity;
import com.fmt.educafloripa.infra.exception.error.InvalidEntity;
import com.fmt.educafloripa.infra.generics.GenericService;
import com.fmt.educafloripa.repository.NotaRepository;
import com.fmt.educafloripa.service.NotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("convertendo entidade nota para dto");

        return new NotaResponse(entity.getId(), entity.getValor(), entity.getDataCriacao(), entity.getAluno().getNome(), entity.getProfessor(), entity.getMateria());
    }

    @Override
    protected NotaEntity paraEntity(NotaRequest requestDto) {

        log.info("convertendo dto nota para entidade");

        return new NotaEntity();
    }

    @Override
    public void atualizar (NotaRequest requestDto, Long id) {
        NotaEntity notaDesatualizado =  pegarEntityPorId(id);

        DocenteEntity professor = notaDesatualizado.getProfessor();
        AlunoEntity aluno = alunoService.pegarEntityPorId(requestDto.aluno());
        MateriaEntity materia = materiaService.pegarEntityPorId(requestDto.materia());

        if (aluno.getTurma().getProfessor() != professor) {
            log.error("O professor {} não esta na turma do aluno {}", professor.getNome(), aluno.getNome());
            throw new InvalidEntity("O professor " + professor.getNome() + " não esta na turma do aluno " + aluno.getNome());
        }
        if (aluno.getTurma().getCurso() != materia.getCurso()) {
            log.error("O aluno {} não tem a metéria {}", aluno.getNome(), materia.getNome());
            throw new InvalidEntity("O aluno " + aluno.getNome() + " não tem a metéria " + materia.getNome());
        }

        NotaEntity notaAtualizado = new NotaEntity(requestDto, aluno, professor, materia);

        notaAtualizado.setId(id);
        notaAtualizado.setDataCriacao(notaDesatualizado.getDataCriacao());

        repository.save(notaAtualizado);

        log.info("entidade nota atualizada com sucesso");
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
            log.error("O professor {} não esta na turma do aluno {}", professor.getNome(), aluno.getNome());
            throw new InvalidEntity("O professor " + professor.getNome() + " não esta na turma do aluno " + aluno.getNome());
        }
        if (aluno.getTurma().getCurso() != materia.getCurso()) {
            log.error("O aluno {} não tem a metéria {}", aluno.getNome(), materia.getNome());
            throw new InvalidEntity("O aluno " + aluno.getNome() + " não tem a metéria " + materia.getNome());
        }

        NotaEntity entity = new NotaEntity(requestDto, aluno, professor, materia);

        repository.save(entity);

        log.info("entidade Nota criada com sucesso");

        return paraDto(entity);
    }

}
