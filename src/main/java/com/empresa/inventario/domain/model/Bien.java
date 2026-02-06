package com.empresa.inventario.domain.model;

import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.enums.TipoMovimiento;
import com.empresa.inventario.domain.exception.DomainException;

public class Bien {

    private int id;
    private String nombre;
    private EstadoBien estado;
    private String descripcion;
    private String categoria;
    private String personaCrea;

    private Bien(String nombre, EstadoBien estado, String descripcion ,String categoria, String personaCrea) {

        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }

        this.nombre = nombre;
        this.estado = estado;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.personaCrea = personaCrea;
    }

    private Bien(int id, String nombre, String descripcion, EstadoBien estado, String categoria, String personaCrea) {

        if(nombre==null || nombre.isEmpty()){
            throw new DomainException("El nombre es obligatorio");
        }
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.personaCrea = personaCrea;
    }

    public static Bien crear(String nombre, String descripcion ,String categoria, String personaCrea) {
        return new Bien(nombre,  EstadoBien.ACTIVO, descripcion,categoria,personaCrea);
    }

    public static Bien hidratar(int id, String nombre, String descripcion, EstadoBien estado, String categoria, String personaCrea) {
        return new Bien(id, nombre, descripcion, estado, categoria,personaCrea);
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getPersonaCrea() {
        return personaCrea;
    }
}
