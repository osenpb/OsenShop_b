package com.osen.ecommerce.auth.infrastructure.controllers;

import com.osen.ecommerce.auth.application.dtos.AuthResponse;
import com.osen.ecommerce.auth.application.dtos.UserResponse;
import com.osen.ecommerce.auth.application.mappers.AuthMapper;
import com.osen.ecommerce.auth.domain.models.User;
import com.osen.ecommerce.auth.domain.services.AuthService;
import com.osen.ecommerce.auth.application.dtos.RegisterRequest;
import com.osen.ecommerce.auth.application.dtos.LoginRequest;
import com.osen.ecommerce.auth.domain.services.UserService;
import com.osen.ecommerce.common.exceptions.EntityNotFound;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth") //
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @Valid RegisterRequest createUserDto) {
        authService.createUser(createUserDto);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequestDTO) throws Exception {

        log.info("Email recibido: {}", loginRequestDTO.email());

        Map<String, String> tokens = authService.login(loginRequestDTO);

        User user = userService.findByEmail(loginRequestDTO.email())
                .orElseThrow(() ->
                        new EntityNotFound("User not found with email " + loginRequestDTO.email())
                );

        UserResponse userResponse = AuthMapper.toDto(user);

        AuthResponse authResponse = new AuthResponse(userResponse, tokens);

        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request){
        String refreshToken = request.get("refresh");
        Map<String, String> newAccessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(newAccessToken);

    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal User user) {
        User me = userService.findByEmail(user.getEmail()).orElseThrow(() -> new EntityNotFound("User not found with email " + user.getEmail()));
        log.info("Usuario encontrado con nombre: " + me.getFirstName());
        UserResponse userResponse = AuthMapper.toDto(me);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/check-status")
    public ResponseEntity<AuthResponse> checkStatus(
            @AuthenticationPrincipal User user,
            HttpServletRequest request) {
        User myUser = userService.findByEmail(user.getEmail()).orElseThrow(() -> new EntityNotFound("User not found with email " + user.getEmail()));
        log.info("User encontrado: {}", user.getFirstName());
        UserResponse userResponse = AuthMapper.toDto(myUser);

        String token = request.getHeader("Authorization")
                .substring(7);
        log.info("token encontrado {}", token);
        AuthResponse authResponse = new AuthResponse(
                userResponse,
                Map.of("accessToken", token,
                        "refreshToken", "asd")
        );
        return ResponseEntity.ok(authResponse);
    }

}
