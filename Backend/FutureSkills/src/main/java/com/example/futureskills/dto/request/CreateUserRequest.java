package com.example.futureskills.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CreateUserRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String fullName;
    @Email(message = "EMAIL_INVALID")
    private String email;
    private LocalDate dob;
}
