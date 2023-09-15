package com.taskmanagement.taskmanagement.service;

import com.taskmanagement.taskmanagement.comparator.TaskComparator;
import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.exception.TaskException;
import com.taskmanagement.taskmanagement.model.TaskStatistics;
import com.taskmanagement.taskmanagement.model.TaskStatus;
import com.taskmanagement.taskmanagement.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) throws Exception {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskException("Task is not found with specified taskId"));
    }

    public Task updateTask(Long taskId, Task task) throws Exception {
        Task savedTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskException("Task is not found with specified id"));
        savedTask.setDescription(task.getDescription());
        savedTask.setStatus(task.getStatus());
        savedTask.setTitle(task.getTitle());
        savedTask.setDueDate(task.getDueDate());
        savedTask.setPriority(task.getPriority());
        if (task.getStatus().equals(TaskStatus.COMPLETED)) {
            savedTask.setCompletionDate(LocalDate.now());
        }
        return taskRepository.save(savedTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksAssignedToUser(Long userId) {
        return taskRepository.findTasksAssignedToUser(userId);
    }

    public void setTaskProgress(Long taskId, int progressPercentage) throws TaskException {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskException("Task not found.");
        }

        if (progressPercentage < 0 || progressPercentage > 100) {
            throw new IllegalArgumentException("Progress percentage must be between 0 and 100.");
        }

        task.setProgressPercentage(progressPercentage);
        taskRepository.save(task);
    }

    public List<Task> getOverdueTasks() {
        LocalDate currentDate = LocalDate.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(currentDate, TaskStatus.COMPLETED);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getCompletedTasksInDateRange(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByStatusAndCompletionDateBetween(TaskStatus.COMPLETED, startDate, endDate);
    }

    public TaskStatistics getTaskStatistics() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);
        double percentageCompleted = (double) completedTasks / totalTasks * 100.0;

        TaskStatistics statistics = new TaskStatistics();
        statistics.setTotalTasks(totalTasks);
        statistics.setCompletedTasks(completedTasks);
        statistics.setPercentageCompleted(percentageCompleted);

        return statistics;
    }

    public void priorityQueueTask(){
        PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>(new TaskComparator());
        /* adding task */
        //taskPriorityQueue.add(task);

        /* To remove the task with the highest priority*/
        taskPriorityQueue.poll();
    }

}
