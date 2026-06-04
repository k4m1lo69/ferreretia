package com.Ferreteria.ms_pedidos.service;

import com.Ferreteria.ms_pedidos.dto.PedidoDTO;
import com.Ferreteria.ms_pedidos.model.Pedido;
import com.Ferreteria.ms_pedidos.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private static final Logger log =
            LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTO save(PedidoDTO dto) {

        Double total =
                dto.getCantidad() * dto.getPrecioUnitario();

        Pedido pedido = Pedido.builder()
                .clienteId(dto.getClienteId())
                .productoId(dto.getProductoId())
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .total(total)
                .estado("PENDIENTE")
                .fecha(LocalDateTime.now())
                .build();

        Pedido saved = pedidoRepository.save(pedido);

        log.info("Pedido creado: {}", saved.getId());

        return convertirADTO(saved);
    }

    public List<PedidoDTO> getAll() {

        return pedidoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO getById(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido no encontrado"));

        return convertirADTO(pedido);
    }

    public PedidoDTO updateEstado(
            Long id,
            String estado) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido no encontrado"));

        pedido.setEstado(estado);

        log.info("Pedido actualizado {}", id);

        return convertirADTO(
                pedidoRepository.save(pedido));
    }

    public boolean delete(Long id) {

        if (pedidoRepository.existsById(id)) {

            pedidoRepository.deleteById(id);

            log.info("Pedido eliminado {}", id);

            return true;
        }

        return false;
    }

    public List<PedidoDTO> getByClienteId(Long clienteId) {

        return pedidoRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private PedidoDTO convertirADTO(Pedido pedido) {

        return PedidoDTO.builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .productoId(pedido.getProductoId())
                .cantidad(pedido.getCantidad())
                .precioUnitario(pedido.getPrecioUnitario())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .build();
    }
}
