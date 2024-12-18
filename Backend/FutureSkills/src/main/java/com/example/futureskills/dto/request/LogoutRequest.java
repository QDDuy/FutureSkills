package com.example.futureskills.dto.request;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class LogoutRequest {
    String token;
}
