package com.Ferreteria.ms_pedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;

    @NotNull(message = "clienteId obligatorio")
    private Long clienteId;

    @NotNull(message = "productoId obligatorio")
    private Long productoId;

    @NotNull(message = "cantidad obligatoria")
    @Positive(message = "cantidad debe ser positiva")
    private Integer cantidad;

    @NotNull(message = "precioUnitario obligatorio")
    @Positive(message = "precioUnitario debe ser positivo")
    private Double precioUnitario;

    private Double total;
    private String estado;
}