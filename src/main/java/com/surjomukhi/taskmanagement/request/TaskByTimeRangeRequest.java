package com.surjomukhi.taskmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskByTimeRangeRequest {

    private Date startDate;
    private Date endDate;
    private int pageNo;
    private int size;
}
