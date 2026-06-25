package com.Ferreteria.ms_reportes.service;

import com.Ferreteria.ms_reportes.dto.ReporteDTO;
import com.Ferreteria.ms_reportes.model.Reporte;
import com.Ferreteria.ms_reportes.repository.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    void save_debeCrearReporteConEstadoGenerado() {
        ReporteDTO dtoEntrada = ReporteDTO.builder()
                .tipo("VENTAS")
                .descripcion("Reporte mensual de ventas")
                .build();

        Reporte reporteGuardado = Reporte.builder()
                .id(1L)
                .tipo("VENTAS")
                .descripcion("Reporte mensual de ventas")
                .fechaGeneracion(LocalDateTime.now())
                .estado("GENERADO")
                .build();

        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporteGuardado);

        ReporteDTO resultado = reporteService.save(dtoEntrada);

        assertEquals("VENTAS", resultado.getTipo());
        assertEquals("GENERADO", resultado.getEstado());
        assertEquals("Reporte mensual de ventas", resultado.getDescripcion());
    }

    @Test
    void getById_cuandoExiste_debeRetornarReporte() {
        Long id = 1L;
        Reporte reporte = Reporte.builder()
                .id(id)
                .tipo("INVENTARIO")
                .descripcion("Reporte de stock")
                .fechaGeneracion(LocalDateTime.now())
                .estado("GENERADO")
                .build();

        when(reporteRepository.findById(id)).thenReturn(Optional.of(reporte));

        ReporteDTO resultado = reporteService.getById(id);

        assertEquals("INVENTARIO", resultado.getTipo());
        assertEquals("GENERADO", resultado.getEstado());
    }

    @Test
    void getById_cuandoNoExiste_debeLanzarExcepcion() {
        Long id = 99L;
        when(reporteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            reporteService.getById(id);
        });
    }

    @Test
    void delete_cuandoExiste_debeEliminarYRetornarTrue() {
        Long id = 1L;
        when(reporteRepository.existsById(id)).thenReturn(true);

        boolean resultado = reporteService.delete(id);

        assertTrue(resultado);
        verify(reporteRepository, times(1)).deleteById(id);
    }

    @Test
    void delete_cuandoNoExiste_debeRetornarFalse() {
        Long id = 99L;
        when(reporteRepository.existsById(id)).thenReturn(false);

        boolean resultado = reporteService.delete(id);

        assertFalse(resultado);
        verify(reporteRepository, never()).deleteById(any());
    }

    @Test
    void getByTipo_debeRetornarListaFiltrada() {
        Reporte reporte = Reporte.builder()
                .id(1L)
                .tipo("VENTAS")
                .descripcion("Reporte mensual")
                .fechaGeneracion(LocalDateTime.now())
                .estado("GENERADO")
                .build();

        when(reporteRepository.findByTipo("VENTAS")).thenReturn(List.of(reporte));

        List<ReporteDTO> resultado = reporteService.getByTipo("VENTAS");

        assertEquals(1, resultado.size());
        assertEquals("VENTAS", resultado.get(0).getTipo());
    }
}