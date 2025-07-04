package com.empresa.empleado.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.empresa.empleado.util.Messages;

@RestControllerAdvice
public class GlobalExceptionHandler {

// Validación de DTOs con @Valid
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String field = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        errors.put(field, message);
    });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
    
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        });
    
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    // Employee not found
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(EmployeeNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Messages.ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Any error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put(Messages.ERROR, Messages.INTERNAL_SERVER_ERROR + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
