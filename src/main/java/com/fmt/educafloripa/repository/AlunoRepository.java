package com.fmt.educafloripa.repository;

import com.fmt.educafloripa.entity.AlunoEntity;
import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    AlunoEntity findByUsuario(UsuarioEntity usuario);
}
