package com.universidad.service;

import com.universidad.dto.InventarioDTO;
import com.universidad.model.Inventario;
import com.universidad.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioDTO> obtenerTodos() {
        return inventarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO obtenerPorId(Long id) {
        Inventario inventario = inventarioRepository.findById(id);
        return inventario != null ? convertToDTO(inventario) : null;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario inventario = convertToEntity(dto);
        Inventario guardado = inventarioRepository.save(inventario);
        return convertToDTO(guardado);
    }

    public InventarioDTO actualizar(Long id, InventarioDTO dto) {
        Inventario existente = inventarioRepository.findById(id);
        if (existente == null) {
            throw new RuntimeException("Inventario no encontrado");
        }
        existente.setCantidad(dto.getCantidad());
        existente.setProductoId(dto.getProductoId());
        existente.setSucursalId(dto.getSucursalId());
        Inventario actualizado = inventarioRepository.save(existente);
        return convertToDTO(actualizado);
    }

    public void eliminar(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Inventario no encontrado");
        }
    }

    private InventarioDTO convertToDTO(Inventario inventario) {
        return new InventarioDTO(inventario.getId(), inventario.getProductoId(), inventario.getSucursalId(), inventario.getCantidad());
    }

    private Inventario convertToEntity(InventarioDTO dto) {
        return new Inventario(dto.getId(), dto.getProductoId(), dto.getSucursalId(), dto.getCantidad());
    }
}