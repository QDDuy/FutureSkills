package com.example.futureskills.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate createAt;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDate.now();
    }
}
