package com.example.futureskills.mapper;

import com.example.futureskills.dto.request.CreateSubCategroy;
import com.example.futureskills.dto.response.SubCategoryResponse;
import com.example.futureskills.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {
    SubCategory toSubCategory(CreateSubCategroy request);
    SubCategoryResponse toSubCategoryResponse(SubCategory subCategory);
    @Mapping(target = "id", ignore = true)
    void updateSubCategory(@MappingTarget SubCategory subCategory, CreateSubCategroy request);
}
