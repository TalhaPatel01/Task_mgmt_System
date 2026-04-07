package com.springboot.taskmgmt.repository;

import com.springboot.taskmgmt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}