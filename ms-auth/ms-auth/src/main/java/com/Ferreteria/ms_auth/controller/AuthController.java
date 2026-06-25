package com.Ferreteria.ms_auth.controller;

import com.Ferreteria.ms_auth.dto.ApiResponse;
import com.Ferreteria.ms_auth.dto.AuthResponse;
import com.Ferreteria.ms_auth.dto.LoginRequest;
import com.Ferreteria.ms_auth.dto.RegisterRequest;
import com.Ferreteria.ms_auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Usuario registrado exitosamente"));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<AuthResponse>> getAll() {
        return ResponseEntity.ok(authService.getAll());
    }
}