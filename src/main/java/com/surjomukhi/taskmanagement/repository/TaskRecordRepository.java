package com.surjomukhi.taskmanagement.repository;

import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TaskRecordRepository extends JpaRepository<TaskRecordEntity, Long> {

}
