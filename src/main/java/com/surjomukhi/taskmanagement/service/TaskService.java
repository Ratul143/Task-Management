package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.request.TaskRequest;
import com.surjomukhi.taskmanagement.utils.BaseResponse;

public interface TaskService {

    BaseResponse createTask(TaskRequest taskRequest);

}
