package com.surjomukhi.taskmanagement.service.implementation;

import com.surjomukhi.taskmanagement.entity.TaskEntity;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

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

}