package com.universidad.repository;


import com.universidad.model.Inventario;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InventarioRepository {
    private final Map<Long, Inventario> inventarios = new ConcurrentHashMap<>();
    private final AtomicLong idContador = new AtomicLong(1);

    public Inventario save(Inventario inventario) {
        if (inventario.getId() == null) {
            inventario.setId(idContador.getAndIncrement());
        }
        inventarios.put(inventario.getId(), inventario);
        return inventario;
    }

    public List<Inventario> findAll() {
        return new ArrayList<>(inventarios.values());
    }

    public Inventario findById(Long id) {
        return inventarios.get(id);
    }

    public boolean existsById(Long id) {
        return inventarios.containsKey(id);
    }

    public void deleteById(Long id) {
        inventarios.remove(id);
    }
}