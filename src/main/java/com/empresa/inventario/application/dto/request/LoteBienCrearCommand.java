package com.empresa.inventario.application.dto.request;

import java.util.List;

public record LoteBienCrearCommand(
        List<BienCrearCommand> bienes
) {}

