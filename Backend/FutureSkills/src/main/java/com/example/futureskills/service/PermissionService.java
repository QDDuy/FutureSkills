package com.example.futureskills.service;

import com.example.futureskills.dto.request.PermissionRequest;
import com.example.futureskills.dto.response.PermissionResponse;
import com.example.futureskills.entity.Permission;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.exceptions.ErrorCode;
import com.example.futureskills.mapper.PermissionMapper;
import com.example.futureskills.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    public PermissionResponse createPermission(PermissionRequest request) {
        Optional<Permission> check = permissionRepository.findByName(request.getName());
        if (check.isPresent()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        } else {
            Permission permission = permissionMapper.toPerMission(request);
            return permissionMapper.toPerMissionResponse(permissionRepository.save(permission));
        }
    }


    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPerMissionResponse).toList();
    }

    public void deletePermission(String permissionName) {
        Optional<Permission> check = permissionRepository.findByName(permissionName);
        if (check.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
            permissionRepository.deleteById(permissionName);

    }
}
