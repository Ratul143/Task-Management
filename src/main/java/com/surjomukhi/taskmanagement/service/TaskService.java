package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.entity.TaskEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    BaseResponse createTask(TaskRequest taskRequest);

    BaseResponse assignTaskToPlayer(TaskRecordEntity taskRecord);

    Page<TaskEntity> getTaskListWithPagination(Pageable pageable);

    BaseResponse updateTask(TaskEntity taskEntity);

    BaseResponse deleteTask(Long id);

    TaskEntity getTaskById(Long id);
}
