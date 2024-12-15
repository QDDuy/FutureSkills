package com.example.futureskills.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequest {
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
    private List<String> roles;
}
