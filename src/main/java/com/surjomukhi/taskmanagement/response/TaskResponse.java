package com.surjomukhi.taskmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private String taskTitle;
    private String taskBrief;
    private ArrayList<String> attachments;
}
