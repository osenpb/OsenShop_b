package com.osen.ecommerce.auth.application.dtos;

import com.osen.ecommerce.auth.domain.models.Role;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
