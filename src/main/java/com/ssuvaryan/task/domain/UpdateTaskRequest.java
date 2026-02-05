package com.ssuvaryan.task.domain;

import com.ssuvaryan.task.domain.entity.TaskPriority;
import com.ssuvaryan.task.domain.entity.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
