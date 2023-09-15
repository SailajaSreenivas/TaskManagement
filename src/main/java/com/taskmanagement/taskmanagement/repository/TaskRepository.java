package com.taskmanagement.taskmanagement.repository;

import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT ta.task FROM TaskAssignment ta WHERE ta.user.userId = :userId")
    List<Task> findTasksAssignedToUser(Long userId);

    List<Task> findByDueDateBeforeAndStatusNot(LocalDate dueDate, TaskStatus status);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByStatusAndCompletionDateBetween(TaskStatus status, LocalDate startDate, LocalDate endDate);

    long countByStatus(TaskStatus status);
}
