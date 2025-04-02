package com.universidad.repository;

import com.universidad.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {
    private final Map<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong idContador = new AtomicLong(1);

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idContador.getAndIncrement());
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    public List<Producto> findAll() {
        return new ArrayList<>(productos.values());
    }

    public Producto findById(Long id) {
        return productos.get(id);
    }

    public boolean existsById(Long id) {
        return productos.containsKey(id);
    }

    public void deleteById(Long id) {
        productos.remove(id);
    }

    public void init() {
        Producto producto1 = Producto.builder()
                .nombre("Laptop Dell Inspiron")
                .descripcion("Laptop con procesador Intel i7 y 16GB RAM")
                .precio(1200.00)
                .stock(10)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Monitor Samsung 24")
                .descripcion("Monitor LED de 24 pulgadas Full HD")
                .precio(200.00)
                .stock(15)
                .build();

        save(producto1);
        save(producto2);
    }
}