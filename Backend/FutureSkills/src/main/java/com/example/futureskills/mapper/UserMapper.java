package com.example.futureskills.mapper;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.request.UpdateUserRequest;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserRequest user);

    UserResponse toResponse(User user);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UpdateUserRequest request);

}
