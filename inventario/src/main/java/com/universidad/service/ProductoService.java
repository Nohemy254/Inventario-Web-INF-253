package com.universidad.service;

import com.universidad.dto.ProductoDTO;
import com.universidad.model.Producto;
import com.universidad.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            productosDTO.add(convertToDTO(producto));
        }
        return productosDTO;
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        return convertToDTO(producto);
    }

    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = convertToEntity(dto);
        Producto guardado = productoRepository.save(producto);
        return convertToDTO(guardado);
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto existente = productoRepository.findById(id);
        if (existente == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());

        Producto guardado = productoRepository.save(existente);
        return convertToDTO(guardado);
    }

    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    private ProductoDTO convertToDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    private Producto convertToEntity(ProductoDTO dto) {
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }
}