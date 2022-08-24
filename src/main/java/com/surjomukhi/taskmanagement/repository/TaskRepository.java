package com.surjomukhi.taskmanagement.repository;

import com.surjomukhi.taskmanagement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
