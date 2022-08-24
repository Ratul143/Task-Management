package com.surjomukhi.taskmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(length = 50, nullable = false)
    private String playerName;

    @Column(length = 250, nullable = false)
    private String playerBio;

    private boolean isActive = false;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Column(updatable = false)
    private String createdBy;

    @UpdateTimestamp
    private Date updatedAt;

    private String updatedBy;

    @ManyToMany
    @JoinColumn(name = "player_id")
    private List<TaskRecordEntity> tasks = new ArrayList<>();
}
