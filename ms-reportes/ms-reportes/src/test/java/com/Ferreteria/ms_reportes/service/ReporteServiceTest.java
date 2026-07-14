package com.Ferreteria.ms_reportes.service;

import com.Ferreteria.ms_reportes.dto.ReporteDTO;
import com.Ferreteria.ms_reportes.model.Reporte;
import com.Ferreteria.ms_reportes.repository.ReporteRepository;
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

@DisplayName("ReporteService Tests")
class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe generar reporte")
    void testSaveReporte() {
        ReporteDTO dto = ReporteDTO.builder()
                .tipo("VENTAS")
                .descripcion("Reporte de ventas mensuales")
                .build();

        Reporte reporte = Reporte.builder()
                .id(1L)
                .tipo("VENTAS")
                .descripcion("Reporte de ventas mensuales")
                .fechaGeneracion(LocalDateTime.now())
                .estado("GENERADO")
                .build();

        when(reporteRepository.save(any())).thenReturn(reporte);
        ReporteDTO resultado = reporteService.save(dto);

        assertNotNull(resultado);
        assertEquals("VENTAS", resultado.getTipo());
    }

    @Test
    @DisplayName("Debe obtener reporte por ID")
    void testGetReporteById() {
        Reporte reporte = new Reporte(1L, "VENTAS", "Reporte ventas", LocalDateTime.now(), "GENERADO");
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        ReporteDTO resultado = reporteService.getById(1L);

        assertEquals("VENTAS", resultado.getTipo());
    }

    @Test
    @DisplayName("Debe filtrar reportes por tipo")
    void testGetReportesByTipo() {
        assertDoesNotThrow(() -> {
            reporteService.getByTipo("VENTAS");
        });
    }
}