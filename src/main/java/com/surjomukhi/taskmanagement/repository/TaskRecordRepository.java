package com.surjomukhi.taskmanagement.repository;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
public interface TaskRecordRepository extends JpaRepository<TaskRecordEntity, Long> {

    Page<TaskRecordEntity> findByIsCompletedOrderByCreatedAtDesc(boolean isCompleted, Pageable pageable);

    List<TaskRecordEntity> findByPlayerOrderByCreatedAtDesc(PlayerEntity player);

    Page<TaskRecordEntity> findByStartDateAfterAndEndDateBefore(Date startDate, Date endDate, Pageable pageable);

}
