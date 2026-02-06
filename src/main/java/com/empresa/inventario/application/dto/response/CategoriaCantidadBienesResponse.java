package com.empresa.inventario.application.dto.response;

public record CategoriaCantidadBienesResponse(
        String categoriaId,
        String nombreCategoria,
        long cantidadBienes
) {}

