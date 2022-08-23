package com.surjomukhi.taskmanagement.service.implementation;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.entity.TaskEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.exception.RecordNotFoundException;
import com.surjomukhi.taskmanagement.repository.PlayerRepository;
import com.surjomukhi.taskmanagement.repository.TaskRecordRepository;
import com.surjomukhi.taskmanagement.repository.TaskRepository;
import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.service.TaskService;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import com.surjomukhi.taskmanagement.utils.CustomCodeGenerator;
import com.surjomukhi.taskmanagement.utils.CustomMessage;
import com.surjomukhi.taskmanagement.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskRecordRepository taskRecordRepository;

    @Autowired
    private CustomCodeGenerator customCodeGenerator;

    @Override
    public BaseResponse createTask(TaskRequest taskRequest) {
        TaskEntity entity = new TaskEntity();
        try {
            BeanUtils.copyProperties(taskRequest, entity);
            entity.setUuid(customCodeGenerator.getGeneratedUuid());
            taskRepository.save(entity);
            log.info("Task" + CustomMessage.SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Task Save Error Message" + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Task" + CustomMessage.SAVE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.CREATED, "Task" + CustomMessage.SAVE_SUCCESS_MESSAGE);
    }

    @Override
    public BaseResponse assignTaskToPlayer(TaskRecordEntity taskRecord) {
        try {
            taskRecord.setUuid(customCodeGenerator.getGeneratedUuid());
            taskRecordRepository.save(taskRecord);
            log.info("Task Record" + CustomMessage.SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Task Record Save Error Message" + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Task Record" + CustomMessage.SAVE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.CREATED, "Task Record" + CustomMessage.SAVE_SUCCESS_MESSAGE);
    }

    @Override
    public Page<TaskEntity> getTaskListWithPagination(Pageable pageable) {
        return taskRepository.findByIsDeletedOrderByCreatedAtDesc(false, pageable);
    }

    @Override
    public BaseResponse updateTask(TaskEntity taskEntity) {
        taskEntity.setUpdatedAt(new Date());
        try {
            taskRepository.save(taskEntity);
            log.info("Task" + CustomMessage.UPDATE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Task Update Error Message : " + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Task" + CustomMessage.UPDATE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.OK, "Task" + CustomMessage.UPDATE_SUCCESS_MESSAGE);
    }

    @Override
    public BaseResponse deleteTask(Long id) {
        try {
            Optional<TaskEntity> entity = taskRepository.findById(id);
            if (entity.isPresent()) {
                TaskEntity taskEntity = entity.get();
                taskEntity.setDeleted(true);
                taskRepository.save(taskEntity);
                log.info("Task" + CustomMessage.DELETE_SUCCESS_MESSAGE);
            } else {
                return new BaseResponse(StatusCode.NO_CONTENT, "Task" + CustomMessage.NO_RECORD_FOUND);
            }
        } catch (Exception e) {
            log.info("Task Delete Error Message : " + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Task" + CustomMessage.DELETE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.OK, "Task" + CustomMessage.DELETE_SUCCESS_MESSAGE);
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (taskEntity.isPresent()) {
            return taskEntity.get();
        } else {
            throw new RecordNotFoundException("No Record Found");
        }
    }
}