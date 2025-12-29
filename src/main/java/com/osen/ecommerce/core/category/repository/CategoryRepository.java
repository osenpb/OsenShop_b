package com.osen.ecommerce.core.category.repository;

import com.osen.ecommerce.core.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
