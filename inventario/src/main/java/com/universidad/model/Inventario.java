package com.universidad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {
    private Long id;
    private Long productoId;
    private Long sucursalId;
    private Integer cantidad;
}
