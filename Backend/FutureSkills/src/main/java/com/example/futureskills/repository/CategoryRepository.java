package com.example.futureskills.repository;

import com.example.futureskills.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<MainCategory, String> {
    Optional<MainCategory> findByName(String name);
}
