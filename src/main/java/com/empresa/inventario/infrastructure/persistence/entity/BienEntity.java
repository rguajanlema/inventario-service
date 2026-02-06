package com.empresa.inventario.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bienes")
public class BienEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false,length = 100)
    private String nombre;
    @Column(name = "descripcion", nullable = false,length = 500)
    private String descripcion;
    @Column(name = "estado", nullable = false,length = 10)
    private String estado;

    @Column(name = "fechaRegistra", nullable = false)
    private LocalDate fechaRegistra;

    @Column(name = "horaRegistra", nullable = false)
    private LocalTime horaRegistra;

    @Column(name = "idCreador", nullable = false, length = 10)
    private String idCreador;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaEntity categoria;
}
