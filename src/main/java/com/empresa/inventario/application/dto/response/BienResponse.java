package com.empresa.inventario.application.dto.response;

import com.empresa.inventario.domain.enums.EstadoBien;

public record BienResponse(int id, String nombre, EstadoBien estado, String categoria) {
}
