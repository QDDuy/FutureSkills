package com.example.futureskills.controller;

import com.example.futureskills.dto.request.CategoryCreateRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.CategoryResponse;
import com.example.futureskills.entity.MainCategory;
import com.example.futureskills.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> list = categoryService.getAll();
        return ApiResponse.<List<CategoryResponse>>builder().result(list).build();
    }

    @PostMapping("/create")
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreateRequest request) {

        return ApiResponse.<CategoryResponse>builder().result(categoryService.create(request)).build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<CategoryResponse> update(@RequestBody @Valid CategoryCreateRequest request, @PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder().
                result(categoryService.update(id, request))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}
