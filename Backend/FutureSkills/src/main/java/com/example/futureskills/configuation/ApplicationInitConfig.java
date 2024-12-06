package com.example.futureskills.configuation;

import com.example.futureskills.entity.User;
import com.example.futureskills.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsUserByUserName("admin")) {
                User user= User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
            }

        };
    }
}
