package com.surjomukhi.taskmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(length = 50, nullable = false)
    private String taskTitle;

    @Column(length = 250)
    private String taskBrief;

    @Column(nullable = true, length = 655555)
    private ArrayList<String> attachments;

    private boolean isDeleted = false;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Column(updatable = false)
    private String createdBy;

    @UpdateTimestamp
    private Date updatedAt;

    private String updatedBy;

    public long daysLeftUntilDeadline(LocalDate date) {
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }
}
