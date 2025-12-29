package com.osen.ecommerce.core.category.controller;

import com.osen.ecommerce.core.category.dto.CategoryResponse;
import com.osen.ecommerce.core.category.mapper.CategoryMapper;
import com.osen.ecommerce.core.category.model.Category;
import com.osen.ecommerce.core.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> categories() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryResponse> categoryResponseList =CategoryMapper.toListDto(categoryList);
        return ResponseEntity.ok(categoryResponseList);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return null;
    }
}
