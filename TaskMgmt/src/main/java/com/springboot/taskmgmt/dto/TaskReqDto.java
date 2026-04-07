package com.springboot.taskmgmt.dto;

import com.springboot.taskmgmt.enums.TaskPriority;
import com.springboot.taskmgmt.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskReqDto(
        @NotNull
        @NotBlank(message = "Title cannot be blank/null")
        @Size(min = 5, max = 100)
        String title,

        @NotNull
        @NotBlank(message = "Description cannot be blank/null")
        @Size(min = 10, max = 255)
        String description,

        LocalDate dueDate,
        TaskPriority taskPriority
) {
}