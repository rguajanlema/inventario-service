package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaJPARepository extends JpaRepository<CategoriaEntity, String> {
}
