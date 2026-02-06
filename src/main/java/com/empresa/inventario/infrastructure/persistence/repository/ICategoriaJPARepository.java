package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.application.dto.response.CategoriaCantidadBienesView;
import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoriaJPARepository extends JpaRepository<CategoriaEntity, String> {

    @Query("""
    SELECT 
        c.id AS categoriaId,
        c.nombre AS nombreCategoria,
        COUNT(b.id) AS cantidadBienes
    FROM CategoriaEntity c
    LEFT JOIN c.bienes b ON b.estado = 'ACTIVO'
    GROUP BY c.id, c.nombre
""")
    List<CategoriaCantidadBienesView> obtenerCantidadBienesPorCategoria();


    @Query("""
    SELECT DISTINCT c
    FROM CategoriaEntity c
    LEFT JOIN FETCH c.bienes
""")
    List<CategoriaEntity> findAllWithBienes();

    @Query("""
    SELECT COUNT(b)
    FROM CategoriaEntity c
    LEFT JOIN c.bienes b
    WHERE c.id = :id
    AND b.estado = :estado
    """)
    Long contarBienesPorEstado(@Param("id") String id, @Param("estado")  String estado);



}
