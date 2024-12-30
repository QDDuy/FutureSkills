package com.example.futureskills.service;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.entity.Role;
import com.example.futureskills.entity.User;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.repository.RoleRepository;
import com.example.futureskills.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    private CreateUserRequest request;
    private User user;
    private Role studentRole;

    @BeforeEach
    void init() {
        // Mock data
        request = CreateUserRequest.builder()
                .userName("test")
                .password("test1234567")
                .fullName("test")
                .email("test@test.com")
                .build();

        studentRole = Role.builder()
                .name("STUDENT")
                .build();

        user = User.builder()
                .id("3956ad65bf9c")
                .userName("test")
                .fullName("test")
                .email("test@test.com")
                .dob(LocalDate.of(2003, 6, 17))
                .build();
    }

}
