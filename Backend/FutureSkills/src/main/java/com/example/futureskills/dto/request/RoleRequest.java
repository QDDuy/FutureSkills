package com.example.futureskills.dto.request;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class RoleRequest {
    private String name;
    private String description;
    private Set<String> permissions;
}
