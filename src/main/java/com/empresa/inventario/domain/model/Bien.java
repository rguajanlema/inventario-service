package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.enums.TipoMovimiento;
import com.empresa.inventario.domain.exception.DomainException;

public class Bien {

    private int id;
    private String nombre;
    private EstadoBien estado;
    private String categoria;

    private Bien(String nombre, EstadoBien estado, String categoria) {

        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }

        this.nombre = nombre;
        this.estado = estado;
        this.categoria = categoria;
    }

    private Bien(int id, String nombre, EstadoBien estado, String categoria) {

        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.categoria = categoria;
    }

    public static Bien crear(String nombre, String categoria){
        return new Bien(nombre, EstadoBien.ACTIVO, categoria);
    }

    public static Bien hidratar(int id, String nombre, EstadoBien estado, String categoria){
        return new Bien(id, nombre, estado, categoria);
    }

    public MovimientoBien darDeBaja(){
        if(estado == EstadoBien.BAJA){
            throw new DomainException("El bien ya está dado de baja");
        }
        EstadoBien anterior = estado;
        estado = EstadoBien.BAJA;
        return new MovimientoBien(id, anterior, estado, TipoMovimiento.BAJA_INDIVIDUAL);
    }

    public MovimientoBien altaConMovimiento() {
        EstadoBien anterior = estado;
        estado = EstadoBien.ACTIVO;
        return new MovimientoBien(id, anterior, estado, TipoMovimiento.ALTA_INDIVIDUAL);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoBien getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }
}
