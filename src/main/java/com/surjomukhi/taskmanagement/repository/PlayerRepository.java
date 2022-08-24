package com.surjomukhi.taskmanagement.repository;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Page<PlayerEntity> findByIsActiveOrderByCreatedAtDesc(boolean isActive, Pageable pageable);
}
