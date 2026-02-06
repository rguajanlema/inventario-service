package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.infrastructure.persistence.entity.BienEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBienJPARepository  extends JpaRepository<BienEntity, Integer> {
}
