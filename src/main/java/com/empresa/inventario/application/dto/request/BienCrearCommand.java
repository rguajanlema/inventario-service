package com.empresa.inventario.application.dto.request;

public record BienCrearCommand(
        String codigo,
        String nombre,
        String descripcion,
        int categoriaId) {
}
