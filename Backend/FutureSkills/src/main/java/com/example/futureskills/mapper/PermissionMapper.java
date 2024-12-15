package com.example.futureskills.mapper;

import com.example.futureskills.dto.request.PermissionRequest;
import com.example.futureskills.dto.response.PermissionResponse;
import com.example.futureskills.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PermissionMapper {
    Permission toPerMission(PermissionRequest permission);
    PermissionResponse toPerMissionResponse(Permission permission);
}
