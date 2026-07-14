package com.Ferreteria.ms_reportes.repository;

import com.Ferreteria.ms_reportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipo(String tipo);

    List<Reporte> findByEstado(String estado);
}