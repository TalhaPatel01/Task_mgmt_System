package com.springboot.taskmgmt.service;

import com.springboot.taskmgmt.dto.DoerSignUpDto;
import com.springboot.taskmgmt.enums.Role;
import com.springboot.taskmgmt.mapper.DoerMapper;
import com.springboot.taskmgmt.mapper.UserMapper;
import com.springboot.taskmgmt.model.Doer;
import com.springboot.taskmgmt.model.User;
import com.springboot.taskmgmt.repository.DoerRepository;
import com.springboot.taskmgmt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoerService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DoerRepository doerRepository;

    public void doerSignUp(DoerSignUpDto doerSignUpDto) {
        Doer doer = DoerMapper.mapToEntity(doerSignUpDto);
        User user = UserMapper.mapToEntity(doerSignUpDto);
        user.setRole(Role.DOER);
        user.setPassword(passwordEncoder.encode(doerSignUpDto.password()));
        userRepository.save(user);

        doer.setUser(user);
        doerRepository.save(doer);
    }
}