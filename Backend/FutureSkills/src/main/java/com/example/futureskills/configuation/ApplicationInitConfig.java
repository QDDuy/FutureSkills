package com.example.futureskills.configuation;

import com.example.futureskills.entity.Permission;
import com.example.futureskills.entity.Role;
import com.example.futureskills.entity.User;
import com.example.futureskills.repository.PermissionRepository;
import com.example.futureskills.repository.RoleRepository;
import com.example.futureskills.repository.UserRepository;
import com.example.futureskills.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Configuration
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Bean
    ApplicationRunner applicationRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (!userRepository.existsUserByUserName("admin")) {
                // Tìm hoặc tạo vai trò admin
                Role adminRole = roleRepository.findByName(com.example.futureskills.enums.Role.ADMIN.name())
                        .orElseGet(() -> {
                            Role newRole = new Role();
                            newRole.setName(com.example.futureskills.enums.Role.ADMIN.name());
                            return roleRepository.save(newRole);
                        });

                // Tạo người dùng admin
                User user = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Set.of(adminRole))
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it");
            }
        };
    }


}
