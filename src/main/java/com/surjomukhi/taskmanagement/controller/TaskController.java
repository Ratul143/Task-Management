package com.surjomukhi.taskmanagement.controller;

import com.surjomukhi.taskmanagement.entity.TaskRecordEntity;
import com.surjomukhi.taskmanagement.request.IdRequest;
import com.surjomukhi.taskmanagement.request.PaginationRequest;
import com.surjomukhi.taskmanagement.request.TaskByTimeRangeRequest;
import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.service.MediaService;
import com.surjomukhi.taskmanagement.service.TaskRecordService;
import com.surjomukhi.taskmanagement.service.TaskService;
import com.surjomukhi.taskmanagement.utils.API;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(API.BASE_API + API.TASK_SERVICE_API)
@Api(tags = "Task Controller API")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRecordService taskRecordService;

    @Autowired
    private MediaService mediaService;

    @PostMapping(API.SAVE)
    @ApiOperation("Create Tasks in the system.")
    public ResponseEntity<BaseResponse> createTask(@Valid @RequestParam(value = "file", required = false) MultipartFile file,
                                                   @ModelAttribute TaskRequest taskRequest) {
        if (file != null ) {
            String attachment = mediaService.saveFile(file);
            taskRequest.setAttachment(attachment);
        }
        BaseResponse response = taskService.createTask(taskRequest);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping(API.ASSIGN_TASK)
    @ApiOperation("Assign Tasks to a/multiple players with schedule in the system.")
    public ResponseEntity<BaseResponse> assignTaskToPlayer(@Valid @RequestBody List<TaskRecordEntity> request) {
           BaseResponse response = taskRecordService.assignTaskToPlayer(request);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping(API.RECORD_LIST)
    @ApiOperation("Returns list of all Task Records in the system.")
    public ResponseEntity<Page<TaskRecordEntity>> getTaskListWithPagination(@RequestBody PaginationRequest request){
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        Page<TaskRecordEntity> response = taskRecordService.getTaskListWithPagination(pageable);
        return new ResponseEntity<Page<TaskRecordEntity>>(response, HttpStatus.OK);
    }

    @GetMapping(API.GET_TASK_BY_PLAYER_ID)
    @ApiOperation("Returns list of tasks assigned to a player in the system.")
    public ResponseEntity<List<TaskRecordEntity>> getTaskById(@RequestBody IdRequest request) {
      return taskRecordService.findByPlayer(request.getId());
    }

    @GetMapping(API.GET_TASK_BY_TIME_RANGE)
    @ApiOperation("Returns list of all the schedule and its assigned tasks and player info according to time range in the system.")
    public ResponseEntity< Page<TaskRecordEntity>> getTaskListByTimeRangeWithPagination(@RequestBody TaskByTimeRangeRequest taskByTimeRangeRequest){
        Page<TaskRecordEntity> response = taskRecordService.getTaskListByTimeRangeWithPagination(taskByTimeRangeRequest);
        return new ResponseEntity< Page<TaskRecordEntity>>(response, HttpStatus.OK);
    }
}
