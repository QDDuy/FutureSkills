package com.example.futureskills.configuation;

import com.example.futureskills.entity.User;
import com.example.futureskills.enums.Role;
import com.example.futureskills.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsUserByUserName("admin")) {
                var role=new HashSet<String>();
                role.add(Role.ADMIN.name());
                log.info("Quyền"+role);
                User user= User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(role)
                        .build();
                userRepository.save(user);
            }

        };
    }
}
