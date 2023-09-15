package com.taskmanagement.taskmanagement.comparator;

import com.taskmanagement.taskmanagement.entity.Task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task>  {
    @Override
    public int compare(Task task1, Task task2) {
        // Compare based on due date first
        int dueDateComparison = task1.getDueDate().compareTo(task2.getDueDate());

        if (dueDateComparison != 0) {
            return dueDateComparison;
        }

        // If due dates are the same, compare based on priority level
        return task1.getPriority().compareTo(task2.getPriority());
    }
}
