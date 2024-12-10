package com.example.futureskills.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UpdateUserRequest {
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
}
