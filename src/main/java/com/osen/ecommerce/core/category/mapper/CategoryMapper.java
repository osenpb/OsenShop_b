package com.osen.ecommerce.core.category.mapper;

import com.osen.ecommerce.core.category.dto.CategoryResponse;
import com.osen.ecommerce.core.category.model.Category;

import java.util.List;

public class CategoryMapper {

    public static CategoryResponse toDto(Category c){
        return new CategoryResponse(
                c.getId(),
                c.getName()
        );
    }

    public static List<CategoryResponse> toListDto(List<Category> categories){
        return categories.stream().map(CategoryMapper::toDto).toList();
    }
}
