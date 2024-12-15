package com.example.futureskills.dto.request;

import com.example.futureskills.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String fullName;
    @Email(message = "EMAIL_INVALID")
    private String email;

    @DobConstraint(min = 6, message = "DOB_INVALID")
    private LocalDate dob;

    private List<String> roles;
}
