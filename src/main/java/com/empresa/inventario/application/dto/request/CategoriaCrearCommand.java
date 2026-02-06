package com.empresa.inventario.application.dto.request;

public record CategoriaCrearCommand(
        String codigo,
        String nombre,
        String creadoPor
) {
}
