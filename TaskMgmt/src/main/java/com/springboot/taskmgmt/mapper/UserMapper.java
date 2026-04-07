package com.springboot.taskmgmt.mapper;

import com.springboot.taskmgmt.dto.DoerSignUpDto;
import com.springboot.taskmgmt.model.User;

public class UserMapper {
    public static User mapToEntity(DoerSignUpDto doerSignUpDto){
        User user = new User();
        user.setUsername(doerSignUpDto.username());
        user.setPassword(doerSignUpDto.password());
        return user;
    }
}