package com.example.futureskills.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class AuthenticationRequest {
    private String userName;
    private String password;
}
