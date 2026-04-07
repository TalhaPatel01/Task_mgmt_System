package com.springboot.taskmgmt.controller;

import com.springboot.taskmgmt.dto.TaskPageResDto;
import com.springboot.taskmgmt.dto.TaskReqDto;
import com.springboot.taskmgmt.dto.TaskResDto;
import com.springboot.taskmgmt.enums.TaskStatus;
import com.springboot.taskmgmt.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskReqDto taskReqDto){
        taskService.addTask(taskReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-all")
    public TaskPageResDto getAllTasks(@RequestParam (value = "page", required = false, defaultValue = "0") int page,
                                            @RequestParam (value = "size", required = false, defaultValue = "0") int size){
        return taskService.getAllTasks(page,size);
    }

    @GetMapping("/get/{taskId}")
    public TaskResDto getTaskById(@PathVariable long taskId){
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/update/status/{taskId}")
    public ResponseEntity<?> updateTaskStatus(@RequestParam TaskStatus taskStatus, @PathVariable long taskId){
        taskService.updateTaskStatus(taskStatus,taskId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable long taskId){
        taskService.deleteTaskById(taskId);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}