package com.empresa.inventario.application.dto.response;

import com.empresa.inventario.domain.enums.EstadoCategoria;

public record CategoriaResponse(
        String codigo,
        String nombre,
        EstadoCategoria estado
) {
}
