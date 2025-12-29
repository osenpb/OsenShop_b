package com.osen.ecommerce.auth.domain.services;

import com.osen.ecommerce.auth.domain.models.User;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface TokenService {
    String generateToken(Authentication authentication);
    String getUserFromToken(String token);
    boolean validateToken(String token);
    String generateRefreshToken(User user);
    Map<String, String> refreshToken(String refreshToken);
}
