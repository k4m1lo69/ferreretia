package com.Ferreteria.ms_reportes.repository;

import com.Ferreteria.ms_reportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByTipo(String tipo);
    List<Reporte> findByEstado(String estado);
}
