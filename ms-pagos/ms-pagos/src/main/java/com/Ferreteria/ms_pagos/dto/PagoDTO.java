package com.Ferreteria.ms_pagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PagoDTO {
    private Long id;

    @NotNull(message = "pedidoId obligatorio")
    private Long pedidoId;

    @NotNull(message = "monto obligatorio")
    @Positive(message = "monto debe ser positivo")
    private Double monto;

    @NotBlank(message = "metodoPago obligatorio")
    private String metodoPago;

    private String estado;
    private String fecha;
}

