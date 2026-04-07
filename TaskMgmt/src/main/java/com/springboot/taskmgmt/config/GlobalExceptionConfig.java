package com.springboot.taskmgmt.config;

import com.springboot.taskmgmt.exception.ResourceNotFoundException;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionConfig {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> getResourceNotFoundException(ResourceNotFoundException e){
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}