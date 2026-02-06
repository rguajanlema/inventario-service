package com.empresa.inventario.application.dto.request;

public record BienCrearCommand(
        String nombre,
        String descripcion,
        String categoriaId,
        String personaCrea
        ) {
}
