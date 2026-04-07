package com.springboot.taskmgmt.service;

import com.springboot.taskmgmt.dto.TaskPageResDto;
import com.springboot.taskmgmt.dto.TaskReqDto;
import com.springboot.taskmgmt.dto.TaskResDto;
import com.springboot.taskmgmt.enums.TaskStatus;
import com.springboot.taskmgmt.exception.ResourceNotFoundException;
import com.springboot.taskmgmt.mapper.TaskMapper;
import com.springboot.taskmgmt.model.Task;
import com.springboot.taskmgmt.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void addTask(@Valid TaskReqDto taskReqDto) {
        Task task = TaskMapper.mapToEntity(taskReqDto);
        task.setTaskStatus(TaskStatus.PENDING);
        taskRepository.save(task);
    }

    public TaskPageResDto getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Task> pageTask = taskRepository.findAll(pageable);
        long totalRecords = pageTask.getTotalElements();
        long totalPages = pageTask.getTotalPages();

        List<TaskResDto> list = pageTask
                .stream()
                .map(TaskMapper::mapToDto)
                .toList();

        return new TaskPageResDto(
             list,
             totalRecords,
             totalPages
        );
    }

    public TaskResDto getTaskById(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("Task with given id not found"));

        return TaskMapper.mapToDto(task);
    }

    public void updateTaskStatus(TaskStatus taskStatus,long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid task id"));

        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
    }

    public void deleteTaskById(long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid task id"));

        taskRepository.deleteById(taskId);
    }
}