package com.springboot.taskmgmt.mapper;

import com.springboot.taskmgmt.dto.TaskReqDto;
import com.springboot.taskmgmt.dto.TaskResDto;
import com.springboot.taskmgmt.model.Task;

public class TaskMapper {

    public static Task mapToEntity(TaskReqDto taskReqDto){
        Task task = new Task();
        task.setTitle(taskReqDto.title());
        task.setDescription(taskReqDto.description());
        task.setDueDate(taskReqDto.dueDate());
        task.setTaskPriority(taskReqDto.taskPriority());
        return task;
    }

    public static TaskResDto mapToDto(Task task){
        return new TaskResDto(
                task.getTask_id(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getTaskStatus()
        );
    }
}