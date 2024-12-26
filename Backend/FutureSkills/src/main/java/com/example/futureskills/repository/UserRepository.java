package com.example.futureskills.repository;

import com.example.futureskills.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String username);
    boolean existsUserByUserName(String userName);
    Page<User> findAll(Pageable pageable);
}
