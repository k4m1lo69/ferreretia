package com.Ferreteria.ms_auth.service;

import com.Ferreteria.ms_auth.dto.LoginRequest;
import com.Ferreteria.ms_auth.dto.AuthResponse;
import com.Ferreteria.ms_auth.model.Usuario;
import com.Ferreteria.ms_auth.repository.UsuarioRepository;
import com.Ferreteria.ms_auth.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("AuthService Tests")
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe hacer login exitosamente")
    void testLoginExitoso() {
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("admin123");

        Usuario usuario = new Usuario(1L, "admin", "hashedPassword", "ADMIN");

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("admin123", "hashedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("admin", "ADMIN")).thenReturn("token123");

        AuthResponse resultado = authService.login(request);

        assertNotNull(resultado);
        assertEquals("token123", resultado.getToken());
    }

    @Test
    @DisplayName("Debe fallar login con credenciales inválidas")
    void testLoginFallo() {
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("wrongPassword");

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(request));
    }
}