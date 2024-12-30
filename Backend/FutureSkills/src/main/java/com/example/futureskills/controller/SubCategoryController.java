package com.example.futureskills.controller;

import com.example.futureskills.dto.request.CreateSubCategroy;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.SubCategoryResponse;
import com.example.futureskills.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub-category")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/get-all")
    ApiResponse<List<SubCategoryResponse>> getAllSubCategories() {
        var results = subCategoryService.getAll();
        return ApiResponse.<List<SubCategoryResponse>>builder().result(results).build();
    }

    @PostMapping("/create")
    ApiResponse<SubCategoryResponse> create(@RequestBody CreateSubCategroy request) {
        var results = subCategoryService.create(request);
        return ApiResponse.<SubCategoryResponse>builder().result(results).build();
    }

    @PutMapping("/update/{id}")
    ApiResponse<SubCategoryResponse> update(@PathVariable String id, @RequestBody CreateSubCategroy request) {
        var results = subCategoryService.update(id, request);
        return ApiResponse.<SubCategoryResponse>builder().result(results).build();
    }
    @DeleteMapping("/delete/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        subCategoryService.delete(id);
        return ApiResponse.<Void>builder().result(null).build();
    }
}
