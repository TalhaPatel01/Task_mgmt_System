package com.springboot.taskmgmt.dto;

public record DoerSignUpDto(
        String name,
        String email,
        String username,
        String password
) {
}