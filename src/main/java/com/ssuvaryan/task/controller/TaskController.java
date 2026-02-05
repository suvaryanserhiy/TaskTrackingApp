package com.ssuvaryan.task.controller;

import com.ssuvaryan.task.domain.CreateTaskRequest;
import com.ssuvaryan.task.domain.dto.CreateTaskRequestDto;
import com.ssuvaryan.task.domain.dto.TaskDto;
import com.ssuvaryan.task.domain.entity.Task;
import com.ssuvaryan.task.mapper.TaskMapper;
import com.ssuvaryan.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
