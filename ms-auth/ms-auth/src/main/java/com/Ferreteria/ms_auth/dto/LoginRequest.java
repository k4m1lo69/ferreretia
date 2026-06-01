package com.Ferreteria.ms_auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username obligatorio")
    private String username;

    @NotBlank(message = "Password obligatorio")
    private String password;
}
