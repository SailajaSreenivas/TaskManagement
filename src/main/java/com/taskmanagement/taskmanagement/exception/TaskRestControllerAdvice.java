package com.taskmanagement.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TaskRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorResponse> handleTaskException(TaskException taskException) {
        ErrorResponse errorResponse = ErrorResponse
                .builder(taskException, HttpStatus.NOT_FOUND, taskException.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse
                .builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

