package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BienTest {

    // ============================
    // Crear bien
    // ============================
    @Test
    void crearBien_nombreValido_funciona() {
        Bien bien = Bien.crear("Televisor", 1);

        assertEquals("Televisor", bien.getNombre());
        assertEquals(EstadoBien.ACTIVO, bien.getEstado());
        assertEquals(1, bien.getCategoria());
    }

    @Test
    void crearBien_nombreVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bien.crear("", 1);
        });
    }

    @Test
    void crearBien_nombreNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bien.crear(null, 1);
        });
    }

    // ============================
    // Hidratar bien
    // ============================
    @Test
    void hidratarBien_nombreValido_funciona() {
        Bien bien = Bien.hidratar(10, "Laptop", EstadoBien.ACTIVO, 2);

        assertEquals(10, bien.getId());
        assertEquals("Laptop", bien.getNombre());
        assertEquals(EstadoBien.ACTIVO, bien.getEstado());
        assertEquals(2, bien.getCategoria());
    }

    @Test
    void hidratarBien_nombreVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bien.hidratar(10, "", EstadoBien.ACTIVO, 2);
        });
    }

    @Test
    void hidratarBien_nombreNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bien.hidratar(10, null, EstadoBien.ACTIVO, 2);
        });
    }

    // ============================
    // Dar de baja
    // ============================
    @Test
    void darDeBaja_bienActivo_funciona() {
        Bien bien = Bien.crear("Televisor", 1);
        bien.darDeBaja();

        assertEquals(EstadoBien.BAJA, bien.getEstado());
    }

    @Test
    void darDeBaja_bienYaBaja_lanzaExcepcion() {
        Bien bien = Bien.crear("Televisor", 1);
        bien.darDeBaja();

        assertThrows(IllegalStateException.class, () -> {
            bien.darDeBaja();
        });
    }

}