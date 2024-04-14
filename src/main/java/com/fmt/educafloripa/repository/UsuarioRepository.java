package com.fmt.educafloripa.repository;

import com.fmt.educafloripa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query
    Optional<UsuarioEntity> findByLogin(String login);
}
