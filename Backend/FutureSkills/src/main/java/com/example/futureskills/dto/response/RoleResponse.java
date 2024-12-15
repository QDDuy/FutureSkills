package com.example.futureskills.dto.response;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class RoleResponse {
    private String name;
    private String description;
    private Set<PermissionResponse> permissions ;

}
