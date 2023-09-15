package com.taskmanagement.taskmanagement.repository;

import com.taskmanagement.taskmanagement.entity.Task;
import com.taskmanagement.taskmanagement.entity.TaskAssignment;
import com.taskmanagement.taskmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment,Long> {

    TaskAssignment findByTaskAndUser(Task task, User user);
}
