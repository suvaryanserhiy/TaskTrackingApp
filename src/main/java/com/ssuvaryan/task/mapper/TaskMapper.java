package com.ssuvaryan.task.mapper;

import com.ssuvaryan.task.domain.CreateTaskRequest;
import com.ssuvaryan.task.domain.dto.CreateTaskRequestDto;
import com.ssuvaryan.task.domain.dto.TaskDto;
import com.ssuvaryan.task.domain.entity.Task;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);

    TaskDto toDto(Task task);
}
