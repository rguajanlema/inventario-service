package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoCategoria;
import com.empresa.inventario.domain.exception.DomainException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria {
    private final String id;
    private String nombre;
    private EstadoCategoria estado;
    private final List<Bien> bienes = new ArrayList<>();

    private Categoria(String id, String nombre, EstadoCategoria estado) {
        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }

        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public static Categoria crear(String id, String nombre) {
        return new Categoria(id, nombre,EstadoCategoria.ACTIVO);
    }

    public static Categoria hidratar(String id, String nombre, EstadoCategoria estado) {
        return new Categoria(id, nombre, estado);
    }

    public void actualizar(String nombre) {
        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }
    public void agregarBien(Bien bien) {
        if(bien==null){
            throw new DomainException("Bien no puede ser nulo");
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

    public String getId() {
        return id;
    }

    public EstadoCategoria getEstado() {
        return estado;
    }
}
