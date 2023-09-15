package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.entity.TaskAssignment;
import com.taskmanagement.taskmanagement.entity.User;
import com.taskmanagement.taskmanagement.repository.TaskAssignmentRepository;
import com.taskmanagement.taskmanagement.repository.TaskRepository;
import com.taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignmentService {

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    public UserRepository userRepository;

    public String assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (task == null || user == null) {
            throw new IllegalArgumentException("Task and user must exist.");
        }

        TaskAssignment existingAssignment = taskAssignmentRepository.findByTaskAndUser(task, user);
        if (existingAssignment != null) {
            throw new IllegalArgumentException("Task is already assigned to the user.");
        }

        TaskAssignment taskAssignment = new TaskAssignment();
        taskAssignment.setTask(task);
        taskAssignment.setUser(user);

        taskAssignmentRepository.save(taskAssignment);

        return "Task assigned to user successfully";
    }
}
