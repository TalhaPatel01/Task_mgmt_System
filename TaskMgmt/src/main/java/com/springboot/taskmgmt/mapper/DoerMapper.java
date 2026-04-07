package com.springboot.taskmgmt.mapper;

import com.springboot.taskmgmt.dto.DoerSignUpDto;
import com.springboot.taskmgmt.model.Doer;

public class DoerMapper {
    public static Doer mapToEntity(DoerSignUpDto doerSignUpDto){
        Doer doer = new Doer();
        doer.setName(doerSignUpDto.name());
        doer.setEmail(doerSignUpDto.email());
        return doer;
    }
}