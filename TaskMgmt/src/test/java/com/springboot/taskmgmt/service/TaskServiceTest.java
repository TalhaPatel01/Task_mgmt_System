package com.springboot.taskmgmt.service;

import com.springboot.taskmgmt.dto.TaskResDto;
import com.springboot.taskmgmt.enums.TaskPriority;
import com.springboot.taskmgmt.enums.TaskStatus;
import com.springboot.taskmgmt.exception.ResourceNotFoundException;
import com.springboot.taskmgmt.model.Task;
import com.springboot.taskmgmt.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getTaskByIdWhenExist(){
        Assertions.assertNotNull(taskService);

        Task task = new Task();
        task.setTask_id(12L);
        task.setTitle("Creating APIs");
        task.setDescription("POST and GET APIs creation");
        task.setDueDate(LocalDate.now());
        task.setTaskPriority(TaskPriority.HIGH);
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        when(taskRepository.findById(12L)).thenReturn(Optional.of(task));

        TaskResDto dto = new TaskResDto(
            task.getTask_id(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getTaskPriority(),
            task.getTaskStatus()
        );

        Assertions.assertEquals(dto,taskService.getTaskById(12L));
    }

    @Test
    public void getTaskByIdWhenNotExist(){
        when(taskRepository.findById(12L)).thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(ResourceNotFoundException.class,()->{
            taskService.getTaskById(12L);
        });

        //check for exception message
        Assertions.assertEquals("Invalid task id",e.getMessage());
    }

    @Test
    public void getAllTasks(){
        Task task = new Task();
        task.setTask_id(12L);
        task.setTitle("Creating APIs");
        task.setDescription("POST and GET APIs creation");
        task.setDueDate(LocalDate.now());
        task.setTaskPriority(TaskPriority.HIGH);
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        List<Task> list = List.of(task);

        Page<Task> pageTask = new PageImpl<>(list);
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page,size);

        when(taskRepository.findAll(pageable)).thenReturn(pageTask);
        Assertions.assertEquals(1,taskService.getAllTasks(0,1).list().size());
    }

    @Test
    public void updateTaskStatusWhenTaskExists() {
        Task task = new Task();
        task.setTask_id(12L);
        task.setTitle("Creating APIs");
        task.setDescription("POST and GET APIs creation");
        task.setDueDate(LocalDate.now());
        task.setTaskPriority(TaskPriority.HIGH);
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        when(taskRepository.findById(12L)).thenReturn(Optional.of(task));

        taskService.updateTaskStatus(TaskStatus.COMPLETED,12L);

        Assertions.assertEquals(TaskStatus.COMPLETED,task.getTaskStatus());
    }

    @Test
    public void updateTaskStatusWhenTaskDoesNotExist() {
        when(taskRepository.findById(12L)).thenReturn(Optional.empty());

        Exception e =  Assertions.assertThrows(ResourceNotFoundException.class,()->{
            taskService.updateTaskStatus(TaskStatus.COMPLETED, 12L);
        });

        Assertions.assertEquals("Invalid task id",e.getMessage());
    }

    @Test
    public void deleteTaskWhenExist(){
        Task task = new Task();
        task.setTask_id(12L);
        task.setTitle("Creating APIs");
        task.setDescription("POST and GET APIs creation");
        task.setDueDate(LocalDate.now());
        task.setTaskPriority(TaskPriority.HIGH);
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        when(taskRepository.findById(12L)).thenReturn(Optional.of(task));

        Assertions.assertDoesNotThrow(() -> {
            taskService.deleteTaskById(12L);
        });
    }

    @Test
    public void deleteTaskWhenTaskNotExist() {
        long taskId = 12L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        Exception e =  Assertions.assertThrows(ResourceNotFoundException.class,()->{
            taskService.deleteTaskById(12L);
        });

        Assertions.assertEquals("Invalid task id",e.getMessage());
    }
}