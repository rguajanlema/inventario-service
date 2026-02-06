package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.application.port.out.IBienRepository;
import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.model.Bien;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BienJPARepositoryAdapter  implements IBienRepository {
    private final IBienJPARepository jpaBienRepository;

    public BienJPARepositoryAdapter(IBienJPARepository jpaBienRepository) {
        this.jpaBienRepository = jpaBienRepository;
    }

    @Override
    public Optional<Bien> findById(int id) {
        return jpaBienRepository.findById(id)
                .map(result -> Bien.hidratar(
                        result.getId(),
                        result.getNombre(),
                        EstadoBien.valueOf(result.getEstado()) ,
                        result.getCategoria().getId()
                ));
    }

    @Override
    public List<Bien> findAll() {
        var result = jpaBienRepository.findAll();
        return result.stream()
                .map(x -> Bien.hidratar(
                x.getId(),
                x.getNombre(),
                EstadoBien.valueOf(x.getEstado()) ,
                x.getCategoria().getId()
        )).toList();
    }
}
