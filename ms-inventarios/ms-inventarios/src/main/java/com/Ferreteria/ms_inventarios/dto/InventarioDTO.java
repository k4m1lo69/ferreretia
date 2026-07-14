package com.Ferreteria.ms_inventarios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    private Long id;

    @NotNull(message = "Producto ID obligatorio")
    private Long productoId;

    @NotNull(message = "Cantidad obligatorio")
    @Min(value = 0, message = "Cantidad inválido")
    private Integer cantidad;

    @NotNull(message = "Cantidad Minima mínimo obligatorio")
    @Min(value = 0, message = "Cantiada Minima mínimo inválido")
    private Integer cantidadMinima;

    @NotBlank(message = "Ubicación obligatoria")
    private String ubicacion;
}