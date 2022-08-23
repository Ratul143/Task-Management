package com.surjomukhi.taskmanagement.controller;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.entity.TaskEntity;
import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.request.IdRequest;
import com.surjomukhi.taskmanagement.request.PaginationRequest;
import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.service.MediaService;
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

@RestController
@RequestMapping(Api.BASE_API + Api.TASK_SERVICE_API)
public class TaskController {

    @Autowired
    TaskService taskService;

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
    public ResponseEntity<BaseResponse> assignTaskToPlayer(@Valid @RequestBody TaskRecordEntity taskRecord) {
        BaseResponse response = taskService.assignTaskToPlayer(taskRecord);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping(Api.LIST)
    public ResponseEntity<Page<TaskEntity>> getTaskListWithPagination(@RequestBody PaginationRequest request){
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        Page<TaskEntity> response = taskService.getTaskListWithPagination(pageable);
        return new ResponseEntity<Page<TaskEntity>>(response, HttpStatus.OK);
    }

    @PutMapping(Api.UPDATE)
    public ResponseEntity<BaseResponse> updateTask(
            @Valid @RequestParam(value = "file", required = false) MultipartFile[] files,
            @ModelAttribute TaskEntity taskEntity) {
        if (files != null ) {
            ArrayList<String> attachments = new ArrayList<>();
            for (MultipartFile file : files){
                String attachment = mediaService.saveFile(file);
                attachments.add(attachment);
            }
            taskEntity.setAttachments(attachments);
        }
        BaseResponse response = taskService.updateTask(taskEntity);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping(Api.DELETE)
    public ResponseEntity<BaseResponse> softDelete(@RequestBody IdRequest request) {
        BaseResponse response = taskService.deleteTask(request.getId());
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @GetMapping(Api.GET_TASK_BY_PLAYER_ID)
    public ResponseEntity<TaskEntity> getTaskById(@RequestBody IdRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.taskService.getTaskById(request.getId()));
    }
}
