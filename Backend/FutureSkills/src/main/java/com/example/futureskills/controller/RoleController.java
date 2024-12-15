package com.example.futureskills.controller;

import com.example.futureskills.dto.request.RoleRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.RoleResponse;
import com.example.futureskills.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roles = roleService.getAllRoles();
        return ApiResponse.<List<RoleResponse>>builder().result(roles).build();
    }

    @PostMapping("/create")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder().result(roleService.createRole(roleRequest)).build();
    }

    @DeleteMapping("/delete/{roleName}")
    public ApiResponse<Void>delete(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ApiResponse.<Void>builder().result(null).build();
    }
}
