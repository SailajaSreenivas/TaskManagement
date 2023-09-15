package com.taskmanagement.taskmanagement.entity;


import com.taskmanagement.taskmanagement.model.TaskPriority;
import com.taskmanagement.taskmanagement.model.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate completionDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Integer progressPercentage;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
