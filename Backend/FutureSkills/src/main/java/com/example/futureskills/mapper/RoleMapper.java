package com.example.futureskills.mapper;

import com.example.futureskills.dto.request.RoleRequest;
import com.example.futureskills.dto.response.RoleResponse;
import com.example.futureskills.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions" ,ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
