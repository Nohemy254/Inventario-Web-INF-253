package com.universidad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {
    private Long id;
    private Usuario usuario;
    private String tipo;
    private LocalDate fecha;
}