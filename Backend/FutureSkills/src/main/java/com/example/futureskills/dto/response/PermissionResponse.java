package com.example.futureskills.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class PermissionResponse {
    private String name;
    private String description;


}
