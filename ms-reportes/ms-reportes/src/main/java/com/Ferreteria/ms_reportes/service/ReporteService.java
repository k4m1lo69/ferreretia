package com.Ferreteria.ms_reportes.service;

import com.Ferreteria.ms_reportes.dto.ReporteDTO;
import com.Ferreteria.ms_reportes.model.Reporte;
import com.Ferreteria.ms_reportes.repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public ReporteDTO save(ReporteDTO dto) {
        Reporte entity = Reporte.builder()
                .tipo(dto.getTipo())
                .descripcion(dto.getDescripcion())
                .fechaGeneracion(LocalDateTime.now())
                .estado("GENERADO")
                .build();
        Reporte saved = reporteRepository.save(entity);
        log.info("Reporte creado: {}", saved.getTipo());
        return convertirADTO(saved);
    }

    public List<ReporteDTO> getAll() {
        log.info("Consultando todos los reportes");
        return reporteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO getById(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Reporte no encontrado: {}", id);
                    return new RuntimeException("Reporte con ID " + id + " no encontrado");
                });
        return convertirADTO(reporte);
    }

    public boolean delete(Long id) {
        if (reporteRepository.existsById(id)) {
            reporteRepository.deleteById(id);
            log.info("Reporte eliminado: {}", id);
            return true;
        }
        log.warn("Intento de eliminar reporte inexistente: {}", id);
        return false;
    }

    public List<ReporteDTO> getByTipo(String tipo) {
        return reporteRepository.findByTipo(tipo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ReporteDTO convertirADTO(Reporte reporte) {
        return ReporteDTO.builder()
                .tipo(reporte.getTipo())
                .descripcion(reporte.getDescripcion())
                .fechaGeneracion(reporte.getFechaGeneracion().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .estado(reporte.getEstado())
                .build();
    }
}
