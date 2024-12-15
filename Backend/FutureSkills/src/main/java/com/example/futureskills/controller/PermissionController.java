package com.example.futureskills.controller;

import com.example.futureskills.dto.request.PermissionRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.PermissionResponse;
import com.example.futureskills.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAllPermissions() {
        List<PermissionResponse> result = permissionService.getAllPermissions();
        return ApiResponse.<List<PermissionResponse>>builder().result(result).build();
    }
    @PostMapping("/create")
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deletePermission(@PathVariable String id) {
        permissionService.deletePermission(id);
        return ApiResponse.<Void>builder().result(null).build();
    }
}
