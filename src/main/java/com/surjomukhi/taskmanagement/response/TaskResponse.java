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

    private Long id;
    private String companyId;
    private String companyName;
    private int departmentId;
    private String departmentName;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private int activityTypeId;
    private String activityTypeName;
    private Long clientId;
    private String clientName;
    private String clientCompanyName;
    private String brief;
    private String actionPlan;
    private ArrayList<String> documents;
    private String locationName;
    private double longitude;
    private double latitude;
    private Date plannedDate;
    private String activityStatus;
}
