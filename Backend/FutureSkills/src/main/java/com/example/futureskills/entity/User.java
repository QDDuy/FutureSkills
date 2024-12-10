package com.example.futureskills.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
