package com.example.futureskills.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
public class IntrospectRequest {
    String token;
}
