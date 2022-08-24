package com.surjomukhi.taskmanagement.service.implementation;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.repository.TaskRecordRepository;
import com.surjomukhi.taskmanagement.request.TaskByTimeRangeRequest;
import com.surjomukhi.taskmanagement.service.TaskRecordService;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import com.surjomukhi.taskmanagement.utils.CustomCodeGenerator;
import com.surjomukhi.taskmanagement.utils.CustomMessage;
import com.surjomukhi.taskmanagement.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TaskRecordServiceImpl implements TaskRecordService {

    @Autowired
    private TaskRecordRepository taskRecordRepository;

    @Autowired
    private CustomCodeGenerator customCodeGenerator;

    @Override
    public BaseResponse assignTaskToPlayer(List<TaskRecordEntity> request) {
        try {
            for (TaskRecordEntity taskRecord : request){
                taskRecord.setUuid(customCodeGenerator.getGeneratedUuid());
                taskRecordRepository.save(taskRecord);
            }
            log.info("Task Record" + CustomMessage.SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Task Record Save Error Message" + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Task Record" + CustomMessage.SAVE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.CREATED, "Task Record" + CustomMessage.SAVE_SUCCESS_MESSAGE);
    }

    @Override
    public Page<TaskRecordEntity> getTaskListWithPagination(Pageable pageable) {
        return taskRecordRepository.findByIsCompletedOrderByCreatedAtDesc(false, pageable);
    }

    @Override
    public ResponseEntity<List<TaskRecordEntity>> findByPlayer(Long playerId) {
        PlayerEntity player = new PlayerEntity();
        player.setId(playerId);
        List<TaskRecordEntity> response =  taskRecordRepository.findByPlayerOrderByCreatedAtDesc(player);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public  Page<TaskRecordEntity> getTaskListByTimeRangeWithPagination(TaskByTimeRangeRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        return taskRecordRepository.findByStartDateAfterAndEndDateBefore(request.getStartDate(), request.getEndDate(), pageable);
    }
}
