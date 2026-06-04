package com.Ferreteria.ms_pedidos.controller;


import com.Ferreteria.ms_pedidos.dto.PedidoDTO;
import com.Ferreteria.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> save(
            @Valid @RequestBody PedidoDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAll() {

        return ResponseEntity.ok(
                pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pedidoService.getById(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> updateEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        return ResponseEntity.ok(
                pedidoService.updateEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        if (pedidoService.delete(id)) {
            return ResponseEntity.ok(
                    "Pedido eliminado");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pedido no encontrado");
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoDTO>> getByCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                pedidoService.getByClienteId(clienteId));
    }
}