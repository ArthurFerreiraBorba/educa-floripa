package com.fmt.educafloripa.repository;

import com.fmt.educafloripa.entity.PapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<PapelEntity, Long> {

    @Query
    Optional<PapelEntity> findByNome(String login);
}
