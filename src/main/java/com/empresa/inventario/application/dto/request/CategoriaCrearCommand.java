package com.empresa.inventario.application.dto.request;

public record CategoriaCrearCommand(
        int codigo,
        String nombre
) {
}
