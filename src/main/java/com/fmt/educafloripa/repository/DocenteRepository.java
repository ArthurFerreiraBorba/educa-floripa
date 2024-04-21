package com.fmt.educafloripa.repository;

import com.fmt.educafloripa.entity.DocenteEntity;
import com.fmt.educafloripa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {

    DocenteEntity findByUsuario(UsuarioEntity usuario);
}
