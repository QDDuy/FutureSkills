package com.example.futureskills.service;

import com.example.futureskills.dto.request.CategoryCreateRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.CategoryResponse;
import com.example.futureskills.dto.response.SubCategoryResponse;
import com.example.futureskills.entity.MainCategory;
import com.example.futureskills.entity.MainCategory;
import com.example.futureskills.entity.SubCategory;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.exceptions.ErrorCode;
import com.example.futureskills.mapper.MainCategoryMapper;
import com.example.futureskills.repository.CategoryRepository;
import com.example.futureskills.repository.SubCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private MainCategoryMapper categoryMapper;
    private SubCategoryRepository subCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, MainCategoryMapper categoryMapper,SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<CategoryResponse> getAll() {
        List<MainCategory> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    public CategoryResponse getById(String id) {
        MainCategory category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        } else {
            return categoryMapper.toCategoryResponse(category);
        }
    }

    public CategoryResponse create(CategoryCreateRequest request) {
        // Kiểm tra danh mục chính đã tồn tại
        Optional<MainCategory> existCategory = categoryRepository.findByName(request.getName());
        if (existCategory.isPresent()) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }

        // Kiểm tra sự tồn tại của danh mục con


        List<SubCategory> subCategories = subCategoryRepository.findAllById(request.getSubCategories());
        log.info("Fetched SubCategories: " + subCategories);

        if (subCategories.size() != request.getSubCategories().size()) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        // Tạo danh mục chính
        MainCategory category = categoryMapper.toCategory(request);

        // Thiết lập mối quan hệ giữa danh mục chính và danh mục con
        Set<SubCategory> subCategorySet = new HashSet<>(subCategories);
        category.setSubCategories(subCategorySet);

        // Lưu danh mục chính
         categoryRepository.save(category);

        // Trả về đối tượng phản hồi
        return categoryMapper.toCategoryResponse(category);
    }


    public CategoryResponse update(String id, CategoryCreateRequest request) {
        MainCategory category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        categoryMapper.updateCategory(request, category);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    public void delete(String id) {
        MainCategory category = categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        if (category != null) {
            categoryRepository.delete(category);
        }

    }
}
