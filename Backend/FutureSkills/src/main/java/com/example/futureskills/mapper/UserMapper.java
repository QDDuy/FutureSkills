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
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "createAt", target = "createAt")
    UserResponse toResponse(User user);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
