package com.osen.ecommerce.core.product.mapper;

import com.osen.ecommerce.core.category.mapper.CategoryMapper;
import com.osen.ecommerce.core.product.dtos.ProductResponse;
import com.osen.ecommerce.core.product.model.Product;

public class ProductMapper {

    public static ProductResponse toDto(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.getImageUrl(),
                CategoryMapper.toDto(p.getCategory()),
                p.getIsActive()
        );
    }

}
