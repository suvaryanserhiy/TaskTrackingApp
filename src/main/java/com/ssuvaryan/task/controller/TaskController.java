package com.ssuvaryan.task.controller;

import com.ssuvaryan.task.domain.CreateTaskRequest;
import com.ssuvaryan.task.domain.UpdateTaskRequest;
import com.ssuvaryan.task.domain.dto.CreateTaskRequestDto;
import com.ssuvaryan.task.domain.dto.TaskDto;
import com.ssuvaryan.task.domain.dto.UpdateTaskRequestDto;
import com.ssuvaryan.task.domain.entity.Task;
import com.ssuvaryan.task.mapper.TaskMapper;
import com.ssuvaryan.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto
    ){
        CreateTaskRequest createTaskRequest = taskMapper.fromDto(createTaskRequestDto);
        Task task = taskService.createTask(createTaskRequest);
        TaskDto createdTaskDto= taskMapper.toDto(task);

        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto
    ){
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDto(updateTaskRequestDto);
        Task task = taskService.updateTask(taskId,updateTaskRequest);

        TaskDto taskDto = taskMapper.toDto(task);

        return ResponseEntity.ok(taskDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks(){
        List<Task> tasks = taskService.listTasks();
        List<TaskDto> taskDtos = tasks.stream().map(taskMapper::toDto).toList();

        return  ResponseEntity.ok(taskDtos);
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId){
        taskService.deleteTask(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
