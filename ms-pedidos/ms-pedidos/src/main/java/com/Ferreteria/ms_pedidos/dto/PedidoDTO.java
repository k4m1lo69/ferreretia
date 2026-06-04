package com.Ferreteria.ms_pedidos.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Cliente ID obligatorio")
    private Long clienteId;

    @NotNull(message = "Producto ID obligatorio")
    private Long productoId;

    @NotNull(message = "Cantidad obligatoria")
    @Min(value = 1, message = "Cantidad mínima 1")
    private Integer cantidad;

    @NotNull(message = "Precio obligatorio")
    @Min(value = 0, message = "Precio inválido")
    private Double precioUnitario;

    private Double total;

    private String estado;
}