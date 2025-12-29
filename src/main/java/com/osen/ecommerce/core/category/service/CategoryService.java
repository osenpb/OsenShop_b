package com.osen.ecommerce.core.category.service;

import com.osen.ecommerce.core.category.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
}
