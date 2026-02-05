package com.ssuvaryan.task.controller;

import com.ssuvaryan.task.domain.dto.ErrorDto;
import com.ssuvaryan.task.exeption.TaskNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("Validation Failed");


        ErrorDto errorDto = new ErrorDto(errorMessage);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex){
        UUID taskNotFoundId = ex.getId();
        String errorMessage = String.format("Task with ID '%s' not found.", taskNotFoundId);
        ErrorDto errorDto = new ErrorDto(errorMessage);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

    }
}
