package com.taskmanagement.taskmanagement.controller;

import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.exception.TaskException;
import com.taskmanagement.taskmanagement.model.TaskStatistics;
import com.taskmanagement.taskmanagement.model.TaskStatus;
import com.taskmanagement.taskmanagement.service.TaskAssignmentService;
import com.taskmanagement.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) throws Exception {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) throws Exception {
        return ResponseEntity.ok(taskService.updateTask(taskId, task));
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<String> assignTaskToUser(@PathVariable("taskId") Long taskId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(taskAssignmentService.assignTaskToUser(taskId, userId));
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getTasksAssignedToUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksAssignedToUser(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}/progress")
    public ResponseEntity<String> setTaskProgress(@PathVariable Long taskId, @RequestParam int progressPercentage) throws TaskException {
        taskService.setTaskProgress(taskId, progressPercentage);
        return ResponseEntity.ok("Task progress updated successfully.");
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> overdueTasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(overdueTasks);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasksInDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Task> completedTasks = taskService.getCompletedTasksInDateRange(startDate, endDate);
        return ResponseEntity.ok(completedTasks);
    }

    @GetMapping("/statistics")
    public ResponseEntity<TaskStatistics> getTaskStatistics() {
        TaskStatistics statistics = taskService.getTaskStatistics();
        return ResponseEntity.ok(statistics);
    }
}
