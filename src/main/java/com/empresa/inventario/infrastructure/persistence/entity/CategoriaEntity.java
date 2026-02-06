package com.empresa.inventario.infrastructure.persistence.entity;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @Column(name = "id", nullable = false, length = 6)
    private String id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estado", nullable = false,length = 10)
    private String estado;

    @Column(name = "fechaRegistra", nullable = false)
    private LocalDate fechaRegistra;

    @Column(name = "horaRegistra", nullable = false)
    private LocalTime horaRegistra;

    @Column(name = "idCreador", nullable = false, length = 10)
    private String idCreador;

    // 🔹 Relación uno a muchos con bienes
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienEntity> bienes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaRegistra() {
        return fechaRegistra;
    }

    public LocalTime getHoraRegistra() {
        return horaRegistra;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public List<BienEntity> getBienes() {
        return bienes;
    }

    public CategoriaEntity(){}
    public CategoriaEntity(String categoriaId){
        this.id = categoriaId;
    }

    public CategoriaEntity(String id, String nombre, String estado, LocalDate fechaRegistra, LocalTime horaRegistra, String idCreador) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaRegistra = fechaRegistra;
        this.horaRegistra = horaRegistra;
        this.idCreador = idCreador;
    }

}
