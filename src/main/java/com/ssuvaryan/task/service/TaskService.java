package com.ssuvaryan.task.service;

import com.ssuvaryan.task.domain.CreateTaskRequest;
import com.ssuvaryan.task.domain.UpdateTaskRequest;
import com.ssuvaryan.task.domain.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(CreateTaskRequest request);

    List<Task> listTasks();

    Task updateTask(UUID taskId, UpdateTaskRequest request);

    void deleteTask(UUID taskId);
}
