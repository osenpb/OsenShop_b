package com.osen.ecommerce.auth.application.dtos;

import java.util.Map;

public record AuthResponse(
        UserResponse user,
        Map<String, String> tokens
) {
}
