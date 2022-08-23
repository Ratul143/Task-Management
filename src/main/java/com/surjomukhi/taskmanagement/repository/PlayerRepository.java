package com.surjomukhi.taskmanagement.repository;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
