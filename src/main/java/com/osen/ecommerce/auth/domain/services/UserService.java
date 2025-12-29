package com.osen.ecommerce.auth.domain.services;

import com.osen.ecommerce.auth.domain.models.User;
import com.osen.ecommerce.auth.application.dtos.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAll();
    User findById(Long id);
    UserResponse save(User user);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);

}
