package com.Ferreteria.ms_producto.service;

import com.Ferreteria.ms_producto.dto.ProductoDTO;
import com.Ferreteria.ms_producto.model.Producto;
import com.Ferreteria.ms_producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ProductoService Tests")
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe crear producto")
    void testSaveProducto() {
        ProductoDTO dto = ProductoDTO.builder()
                .nombre("Martillo")
                .descripcion("Martillo de acero")
                .precio(50.0)
                .sku("MART001")
                .categoria("Herramientas")
                .marca("Total")
                .build();

        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Martillo")
                .descripcion("Martillo de acero")
                .precio(50.0)
                .sku("MART001")
                .categoria("Herramientas")
                .marca("Total")
                .build();

        when(productoRepository.save(any())).thenReturn(producto);
        ProductoDTO resultado = productoService.save(dto);

        assertNotNull(resultado);
        assertEquals("Martillo", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe obtener producto por ID")
    void testGetProductoById() {
        Producto producto = new Producto(1L, "Martillo", "Acero", 50.0, "MART001", "Herramientas", "Total");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ProductoDTO resultado = productoService.getById(1L);

        assertEquals("Martillo", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe actualizar producto")
    void testUpdateProducto() {
        Long id = 1L;
        ProductoDTO dto = ProductoDTO.builder().nombre("Martillo Actualizado").build();
        Producto productoExistente = new Producto(1L, "Martillo", "Acero", 50.0, "MART001", "Herramientas", "Total");
        Producto productoActualizado = new Producto(1L, "Martillo Actualizado", "Acero", 50.0, "MART001", "Herramientas", "Total");

        when(productoRepository.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any())).thenReturn(productoActualizado);

        ProductoDTO resultado = productoService.update(id, dto);

        assertEquals("Martillo Actualizado", resultado.getNombre());
    }

    @Test
    @DisplayName("Debe eliminar producto")
    void testDeleteProducto() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        boolean resultado = productoService.delete(1L);
        assertTrue(resultado);
    }
}