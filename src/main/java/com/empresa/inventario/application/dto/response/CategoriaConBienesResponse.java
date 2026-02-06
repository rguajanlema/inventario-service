package com.empresa.inventario.application.dto.response;

import java.util.List;

public record CategoriaConBienesResponse(
        String id,
        String nombre,
        List<BienResponse> bienes
) {}

