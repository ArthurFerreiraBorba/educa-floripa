package com.fmt.educafloripa.repository;

import com.fmt.educafloripa.entity.CursoEntity;
import com.fmt.educafloripa.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
    List<MateriaEntity> findAllByCurso(CursoEntity curso);
}
