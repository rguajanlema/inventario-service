package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.TipoMovimiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria {
    private final int id;
    private String nombre;
    private final List<Bien> bienes = new ArrayList<>();

    private Categoria(int id, String nombre) {
        if(nombre==null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        this.id = id;
        this.nombre = nombre;
    }

    public static Categoria crear(int id, String nombre) {
        return new Categoria(id, nombre);
    }

    public static Categoria hidratar(int id, String nombre) {
        return new Categoria(id, nombre);
    }

    public void actualizar(String nombre) {
        if(nombre==null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }
    public void agregarBien(Bien bien) {
        if(bien==null){
            throw new IllegalArgumentException("Bien no puede ser nulo");
        }
        bienes.add(bien);
    }

    public List<Bien> getBienes() {
        return Collections.unmodifiableList(bienes); // evita modificaciones externas
    }

    public List<MovimientoBien> altaLote(List<Bien> nuevosBienes) {
        List<MovimientoBien> movimientos = new ArrayList<>();
        for (Bien bien : nuevosBienes) {
            bienes.add(bien);
            movimientos.add(bien.altaConMovimiento());
        }
        return movimientos;
    }

    public List<MovimientoBien> bajaLote(List<Integer> idsBienes) {
        List<MovimientoBien> movimientos = new ArrayList<>();
        for (Bien bien : bienes) {
            if (idsBienes.contains(bien.getId())) {
                movimientos.add(bien.darDeBaja());
            }
        }
        return movimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
