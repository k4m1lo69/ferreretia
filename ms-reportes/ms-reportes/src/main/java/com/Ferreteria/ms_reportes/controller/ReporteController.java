package com.Ferreteria.ms_reportes.controller;

import com.Ferreteria.ms_reportes.dto.ReporteDTO;
import com.Ferreteria.ms_reportes.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> save(@Valid @RequestBody ReporteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> getAll() {
        return ResponseEntity.ok(reporteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (reporteService.delete(id)) {
            return ResponseEntity.ok("Reporte eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte no encontrado");
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ReporteDTO>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(reporteService.getByTipo(tipo));
    }
}
