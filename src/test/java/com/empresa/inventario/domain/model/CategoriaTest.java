package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoriaTest {

    // ============================
    // Crear categoría
    // ============================
    @Test
    void crearCategoria_nombreValido_funciona() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");

        assertEquals("C-001", cat.getId());
        assertEquals("Electrónica", cat.getNombre());
    }

    @Test
    void crearCategoria_nombreVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Categoria.crear("C-001", "");
        });
    }

    @Test
    void crearCategoria_nombreNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Categoria.crear("C-001", null);
        });
    }

    // ============================
    // Hidratar categoría
    // ============================
    @Test
    void hidratarCategoria_nombreValido_funciona() {
        Categoria cat = Categoria.hidratar("C-002", "Hogar");

        assertEquals("C-002", cat.getId());
        assertEquals("Hogar", cat.getNombre());
    }

    @Test
    void hidratarCategoria_nombreVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Categoria.hidratar("C-002", "");
        });
    }

    @Test
    void hidratarCategoria_nombreNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Categoria.hidratar("C-002", null);
        });
    }

    // ============================
    // Actualizar categoría
    // ============================
    @Test
    void actualizarCategoria_nombreValido_funciona() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");
        cat.actualizar("Hogar");

        assertEquals("Hogar", cat.getNombre());
    }

    @Test
    void actualizarCategoria_nombreVacio_lanzaExcepcion() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");

        assertThrows(IllegalArgumentException.class, () -> {
            cat.actualizar("");
        });
    }

    @Test
    void actualizarCategoria_nombreNulo_lanzaExcepcion() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");

        assertThrows(IllegalArgumentException.class, () -> {
            cat.actualizar(null);
        });
    }

    // ============================
    // Agregar bien
    // ============================
    @Test
    void agregar_bienNulo_lanzaExcepcion() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");
        assertThrows(IllegalArgumentException.class, () -> {
            cat.agregarBien(null);
        });
    }
    @Test
    void agregar_bienNoNulo_NolanzaExcepcion() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");
        cat.agregarBien(Bien.crear("Televisor", cat.getId()));

        assertEquals("C-001", cat.getBienes().size());

        cat.agregarBien(Bien.crear("Laptop", cat.getId()));

        assertEquals(2, cat.getBienes().size());
    }

    // ============================
    // Dar de baja bien
    // ============================
    @Test
    void darDeBajaLote_todosBienesCambianAEstadoBaja() {
        Categoria cat = Categoria.crear("C-001", "Electrónica");

        Bien tv = Bien.crear("Televisor", cat.getId());
        Bien laptop = Bien.crear("Laptop", cat.getId());

        cat.agregarBien(tv);
        cat.agregarBien(laptop);

        // IDs del lote a dar de baja
        List<Integer> lote = List.of(tv.getId(), laptop.getId());

        // Acción
        cat.bajaLote(lote);

        // Verificaciones
        assertEquals(EstadoBien.BAJA, tv.getEstado());
        assertEquals(EstadoBien.BAJA, laptop.getEstado());
    }



}