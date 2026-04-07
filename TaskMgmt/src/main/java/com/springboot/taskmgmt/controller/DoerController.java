package com.springboot.taskmgmt.controller;

import com.springboot.taskmgmt.dto.DoerSignUpDto;
import com.springboot.taskmgmt.service.DoerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doer")
@AllArgsConstructor
public class DoerController {
    private final DoerService doerService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> doerSignUp(@RequestBody DoerSignUpDto doerSignUpDto){
        doerService.doerSignUp(doerSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}