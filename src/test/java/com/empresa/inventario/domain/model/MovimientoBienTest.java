package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.enums.TipoMovimiento;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovimientoBienTest {
    @Test
    void crearMovimiento_registraCorrectamenteLosDatos() {
        int bienId = 1;
        EstadoBien estadoAnterior = EstadoBien.ACTIVO;
        EstadoBien estadoNuevo = EstadoBien.BAJA;
        TipoMovimiento tipo = TipoMovimiento.BAJA_INDIVIDUAL;

        MovimientoBien movimiento = new MovimientoBien(bienId, estadoAnterior, estadoNuevo, tipo);

        assertEquals(bienId, movimiento.getBienId());
        assertEquals(estadoAnterior, movimiento.getEstadoAnterior());
        assertEquals(estadoNuevo, movimiento.getEstadoNuevo());
        assertEquals(tipo, movimiento.getDescripcion());

        // Verifica que la fecha se asignó (aproximadamente ahora)
        assertTrue(movimiento.getFecha().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(movimiento.getFecha().isAfter(LocalDateTime.now().minusSeconds(1)));
    }
}