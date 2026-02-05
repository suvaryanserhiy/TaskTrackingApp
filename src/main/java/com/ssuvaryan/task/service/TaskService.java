package com.ssuvaryan.task.service;

import com.ssuvaryan.task.domain.CreateTaskRequest;
import com.ssuvaryan.task.domain.entity.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
