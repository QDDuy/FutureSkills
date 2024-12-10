package com.example.futureskills.service;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.request.UpdateUserRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.entity.User;
import com.example.futureskills.enums.Role;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.exceptions.ErrorCode;
import com.example.futureskills.mapper.UserMapper;
import com.example.futureskills.repository.UserRepository;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getAll() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        List<User> listUser = userRepository.findAll();
        return listUser.stream().map(userMapper::toResponse).toList();
    }

    @PostAuthorize("returnObject.userName==authentication.name")
    public UserResponse getUser(String id) {
        log.info("Inmethod get user by id");
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse getMyInfo() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        String name = authentication.getName();
        log.info("Fetching information for user: {}", name);

        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));


        return userMapper.toResponse(user);
    }


    public UserResponse createUser(CreateUserRequest request) {
        Optional<User> findExistUser = userRepository.findByUserName(request.getUserName());
        HashSet<String> roles = new HashSet<>();
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            roles.addAll(request.getRoles());
        } else {
            roles.add(Role.STUDENT.name());
        }
        if (findExistUser.isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED); // Ném lỗi nếu user đã tồn tại
        }
        // Nếu không tồn tại, tạo mới user
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        return userMapper.toResponse(userRepository.save(user));
    }


    public UserResponse updateUser(String id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(request, user);
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userMapper.toResponse(userRepository.save(user));

    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.deleteById(user.getId());
    }
}
