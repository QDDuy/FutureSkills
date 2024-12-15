package com.example.futureskills.service;

import com.example.futureskills.dto.request.RoleRequest;
import com.example.futureskills.dto.response.RoleResponse;
import com.example.futureskills.entity.Permission;
import com.example.futureskills.entity.Role;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.exceptions.ErrorCode;
import com.example.futureskills.mapper.RoleMapper;
import com.example.futureskills.repository.PermissionRepository;
import com.example.futureskills.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionRepository permissionRepository;

    public List<RoleResponse> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roles1 -> roleMapper.toRoleResponse(roles1)).toList();
    }

    public RoleResponse createRole(RoleRequest roleRequest) {
        // Kiểm tra xem role có tồn tại không
        Optional<Role> check = roleRepository.findByName(roleRequest.getName());
        if (check != null) {
            throw new AppException(ErrorCode.ROLE_EXISTED); // Mã lỗi ROLE_EXISTED
        }

        List<Permission> checkPermissions = permissionRepository.findAllById(roleRequest.getPermissions());
        if (checkPermissions.size() != roleRequest.getPermissions().size()) {
            throw new RuntimeException("Some permissions do not exist");
        }

        // Tạo đối tượng Role mới và gán permissions cho role đó
        Role role = roleMapper.toRole(roleRequest);
        role.setPermissions(new HashSet<>(checkPermissions)); // Thiết lập permissions

        // Lưu role vào cơ sở dữ liệu
        roleRepository.save(role);

        // Trả về RoleResponse
        return roleMapper.toRoleResponse(role);
    }

    public Role findByName(String name) {
        Optional<Role> check = roleRepository.findByName(name);
        if (check.isEmpty()){
            throw new RuntimeException("Role not found");
        }
        return check.get();
    }

    public void deleteRole(String roleName) {
        var check = roleRepository.findByName(roleName);
        if (check == null) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(roleName);
    }
}
