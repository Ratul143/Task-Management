package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.request.TaskByTimeRangeRequest;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskRecordService {

    BaseResponse assignTaskToPlayer(List<TaskRecordEntity> taskRecord);

    Page<TaskRecordEntity> getTaskListWithPagination(Pageable pageable);

    ResponseEntity<List<TaskRecordEntity>> findByPlayer(Long playerId);

    Page<TaskRecordEntity> getTaskListByTimeRangeWithPagination(TaskByTimeRangeRequest request);

}
