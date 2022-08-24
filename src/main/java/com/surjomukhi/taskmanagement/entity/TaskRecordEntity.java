package com.surjomukhi.taskmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_record")
public class TaskRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column( nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

//    @ManyToMany
//    @JoinColumn(name = "task_record_id")
//    private List<PlayerEntity> player = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    private TaskEntity task;

    @ManyToOne
    private PlayerEntity player;

    @ManyToOne
    private TaskEntity task;

    private boolean isCompleted = false;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Column(updatable = false)
    private String createdBy;

    @UpdateTimestamp
    private Date updatedAt;

    private String updatedBy;

}
