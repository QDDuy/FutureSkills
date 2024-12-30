package com.example.futureskills.repository;

import com.example.futureskills.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory, String> {
    SubCategory findByName(String name);
}
