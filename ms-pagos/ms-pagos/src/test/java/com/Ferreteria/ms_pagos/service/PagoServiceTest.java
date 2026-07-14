package com.Ferreteria.ms_pagos.service;

import com.Ferreteria.ms_pagos.dto.PagoDTO;
import com.Ferreteria.ms_pagos.model.Pago;
import com.Ferreteria.ms_pagos.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PagoService Tests")
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe crear pago")
    void testSavePago() {
        PagoDTO dto = PagoDTO.builder()
                .pedidoId(1L)
                .monto(500.0)
                .metodoPago("TARJETA")
                .build();

        Pago pago = Pago.builder()
                .id(1L)
                .pedidoId(1L)
                .monto(500.0)
                .metodoPago("TARJETA")
                .estado("PENDIENTE")
                .fecha(LocalDateTime.now())
                .build();

        when(pagoRepository.save(any())).thenReturn(pago);
        PagoDTO resultado = pagoService.save(dto);

        assertNotNull(resultado);
        assertEquals(500.0, resultado.getMonto());
    }

    @Test
    @DisplayName("Debe obtener pago por ID")
    void testGetPagoById() {
        Pago pago = new Pago(1L, 1L, 500.0, "TARJETA", "PENDIENTE", LocalDateTime.now());
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        PagoDTO resultado = pagoService.getById(1L);

        assertEquals(500.0, resultado.getMonto());
    }

    @Test
    @DisplayName("Debe actualizar estado de pago")
    void testUpdateEstadoPago() {
        Pago pago = new Pago(1L, 1L, 500.0, "TARJETA", "PENDIENTE", LocalDateTime.now());
        Pago pagoActualizado = new Pago(1L, 1L, 500.0, "TARJETA", "COMPLETADO", LocalDateTime.now());

        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any())).thenReturn(pagoActualizado);

        PagoDTO resultado = pagoService.updateEstado(1L, "COMPLETADO");

        assertEquals("COMPLETADO", resultado.getEstado());
    }
}