package com.example.futureskills.controller;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.request.UpdateUserRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get_all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Page<UserResponse>> getAllUsers(Pageable pageable) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Lấy danh sách người dùng đã phân trang
        Page<UserResponse> userResponses = userService.getAll(pageable);

        // Log tên người dùng và các quyền của họ
        log.info("UserName:{}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        // Trả về ApiResponse với dữ liệu phân trang
        return ApiResponse.<Page<UserResponse>>builder()
                .result(userResponses)
                .build();
    }


    @GetMapping("/get/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        var result = userService.getUser(id);
        return ApiResponse.<UserResponse>builder()
                .result(result)
                .build();
    }
    @GetMapping("/my_info")
    public ApiResponse<UserResponse> getMyInfo() {
        var result = userService.getMyInfo();
        return ApiResponse.<UserResponse>builder().result(result).build();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody  @Valid UpdateUserRequest request) {
        UserResponse userResponse = userService.updateUser(id, request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder().build();
    }
}
