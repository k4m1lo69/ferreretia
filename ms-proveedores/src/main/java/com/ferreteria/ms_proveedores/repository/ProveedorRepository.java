package com.ferreteria.ms_proveedores.controller;


import com.ferreteria.ms_proveedores.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByRut(String rut);
    Optional<Proveedor> findByRut(String rut);
}