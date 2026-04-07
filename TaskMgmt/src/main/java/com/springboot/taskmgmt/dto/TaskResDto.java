package com.springboot.taskmgmt.dto;

import com.springboot.taskmgmt.enums.TaskPriority;
import com.springboot.taskmgmt.enums.TaskStatus;

import java.time.LocalDate;

public record TaskResDto(
    long task_id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority taskPriority,
    TaskStatus taskStatus
) {
}