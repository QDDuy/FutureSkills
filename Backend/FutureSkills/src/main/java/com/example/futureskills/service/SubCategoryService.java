package com.example.futureskills.service;

import com.example.futureskills.dto.request.CreateSubCategroy;
import com.example.futureskills.dto.response.SubCategoryResponse;
import com.example.futureskills.entity.SubCategory;
import com.example.futureskills.exceptions.AppException;
import com.example.futureskills.exceptions.ErrorCode;
import com.example.futureskills.mapper.SubCategoryMapper;
import com.example.futureskills.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryService {
    private SubCategoryRepository subCategoryRepository;
    private SubCategoryMapper subCategoryMapper;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    public List<SubCategoryResponse> getAll() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return subCategories.stream().map(subCategoryMapper::toSubCategoryResponse).toList();
    }

    public SubCategoryResponse create(CreateSubCategroy request) {
        SubCategory subCategory = subCategoryRepository.findByName(request.getName());
        if (subCategory != null) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        subCategory = subCategoryMapper.toSubCategory(request);
        subCategoryRepository.save(subCategory);
        return subCategoryMapper.toSubCategoryResponse(subCategory);
    }

    public SubCategoryResponse update(String id, CreateSubCategroy request) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        subCategoryMapper.updateSubCategory(subCategory, request);
        subCategoryRepository.save(subCategory);
        return subCategoryMapper.toSubCategoryResponse(subCategory);

    }

    public void delete(String id) {
        subCategoryRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        subCategoryRepository.deleteById(id);

    }
}
