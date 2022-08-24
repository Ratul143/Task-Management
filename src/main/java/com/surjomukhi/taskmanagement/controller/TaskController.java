package com.surjomukhi.taskmanagement.controller;

import com.surjomukhi.taskmanagement.entity.TaskEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.request.IdRequest;
import com.surjomukhi.taskmanagement.request.PaginationRequest;
import com.surjomukhi.taskmanagement.request.TaskByTimeRangeRequest;
import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.service.MediaService;
import com.surjomukhi.taskmanagement.service.TaskRecordService;
import com.surjomukhi.taskmanagement.service.TaskService;
import com.surjomukhi.taskmanagement.utils.Api;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Api.BASE_API + Api.TASK_SERVICE_API)
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRecordService taskRecordService;

    @Autowired
    private MediaService mediaService;

    @PostMapping(Api.SAVE)
    public ResponseEntity<BaseResponse> createTask(@Valid @RequestParam(value = "file", required = false) MultipartFile[] files,
                                                   @ModelAttribute TaskRequest taskRequest) {
        if (files != null ) {
           ArrayList<String> attachments = new ArrayList<>();
            for (MultipartFile file : files){
                String attachment = mediaService.saveFile(file);
                attachments.add(attachment);
            }
            taskRequest.setAttachments(attachments);
        }
        BaseResponse response = taskService.createTask(taskRequest);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping(Api.ASSIGN_TASK)
    public ResponseEntity<BaseResponse> assignTaskToPlayer(@Valid @RequestBody List<TaskRecordEntity> request) {
           BaseResponse response = taskRecordService.assignTaskToPlayer(request);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping(Api.RECORD_LIST)
    public ResponseEntity<Page<TaskRecordEntity>> getTaskListWithPagination(@RequestBody PaginationRequest request){
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        Page<TaskRecordEntity> response = taskRecordService.getTaskListWithPagination(pageable);
        return new ResponseEntity<Page<TaskRecordEntity>>(response, HttpStatus.OK);
    }

    @GetMapping(Api.GET_TASK_BY_PLAYER_ID)
    public ResponseEntity<List<TaskRecordEntity>> getTaskById(@RequestBody IdRequest request) {
      return taskRecordService.findByPlayer(request.getId());
    }

    @GetMapping(Api.GET_TASK_BY_TIME_RANGE)
    public ResponseEntity< Page<TaskRecordEntity>> getTaskListByTimeRangeWithPagination(@RequestBody TaskByTimeRangeRequest taskByTimeRangeRequest){
        Page<TaskRecordEntity> response = taskRecordService.getTaskListByTimeRangeWithPagination(taskByTimeRangeRequest);
        return new ResponseEntity< Page<TaskRecordEntity>>(response, HttpStatus.OK);
    }
}
