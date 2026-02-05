package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.enums.TipoMovimiento;

import java.time.LocalDateTime;

public class MovimientoBien {
    private final int bienId;
    private final EstadoBien estadoAnterior;
    private final EstadoBien estadoNuevo;
    private final TipoMovimiento descripcion;
    private final LocalDateTime fecha;

    public MovimientoBien(int bienId, EstadoBien estadoAnterior, EstadoBien estadoNuevo, TipoMovimiento descripcion) {
        this.bienId = bienId;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public int getBienId() { return bienId; }
    public EstadoBien getEstadoAnterior() { return estadoAnterior; }
    public EstadoBien getEstadoNuevo() { return estadoNuevo; }
    public TipoMovimiento getDescripcion() { return descripcion; }
    public LocalDateTime getFecha() { return fecha; }
}

