package com.example.futureskills.mapper;

import com.example.futureskills.dto.request.CategoryCreateRequest;
import com.example.futureskills.dto.response.CategoryResponse;
import com.example.futureskills.entity.MainCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MainCategoryMapper {
    @Mapping(target = "subCategories" , ignore = true)
    MainCategory toCategory(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse toCategoryResponse(MainCategory category);
    @Mapping(target = "subCategories", ignore = true)
    void updateCategory(CategoryCreateRequest request, @MappingTarget MainCategory category);
}
