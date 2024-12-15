package com.example.futureskills.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class Permission {
    @Id
    private String name;
    private String description;

    public Permission(String name) {
    }
}
