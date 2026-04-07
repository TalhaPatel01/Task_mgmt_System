package com.springboot.taskmgmt.dto;

import java.util.List;

public record TaskPageResDto(
        List<TaskResDto> list,
        long totalRecords,
        long totalPages
) {
}