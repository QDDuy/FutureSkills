package com.example.futureskills.controller;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.request.UpdateUserRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.entity.User;
import com.example.futureskills.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
        @Autowired
        private UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses =userService.getAll();
        log.info(userResponses.toString());
        return ApiResponse.<List<UserResponse>>builder()
                .result(userResponses)
                .build();
    }
    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }
    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id,@RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.updateUser(id, request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable  String id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder().build();
    }
}
