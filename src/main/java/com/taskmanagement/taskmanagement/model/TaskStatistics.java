package com.taskmanagement.taskmanagement.model;

import lombok.Data;

@Data
public class TaskStatistics {
    private long totalTasks;
    private long completedTasks;
    private double percentageCompleted;
}
