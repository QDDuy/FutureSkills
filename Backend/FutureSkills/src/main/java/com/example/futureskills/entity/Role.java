package com.example.futureskills.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class Role {
    @Id
    private String name;
    private String description;
    @ManyToMany
    private Set<Permission> permissions;


    public Role(String name) {
        this.name = name;
    }
}
