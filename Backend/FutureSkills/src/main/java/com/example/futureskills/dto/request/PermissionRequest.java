package com.example.futureskills.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class PermissionRequest {
    private String name;
    private String description;


}
