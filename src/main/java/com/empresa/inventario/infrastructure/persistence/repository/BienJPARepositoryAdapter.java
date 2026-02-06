package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.application.port.out.IBienRepository;
import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.model.Bien;
import com.empresa.inventario.infrastructure.persistence.entity.BienEntity;
import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class BienJPARepositoryAdapter  implements IBienRepository {
    private final IBienJPARepository jpaBienRepository;

    public BienJPARepositoryAdapter(IBienJPARepository jpaBienRepository
                                    ) {
        this.jpaBienRepository = jpaBienRepository;
    }

    @Override
    public Optional<Bien> findById(int id) {
        return jpaBienRepository.findById(id)
                .map(result -> Bien.hidratar(
                        result.getId(),
                        result.getNombre(),
                        result.getDescripcion(),
                        EstadoBien.valueOf(result.getEstado()) ,
                        result.getCategoria().getId(),
                        result.getIdCreador()
                ));
    }

    @Override
    public List<Bien> findAll() {
        var result = jpaBienRepository.findAll();
        return result.stream()
                .map(x -> Bien.hidratar(
                x.getId(),
                x.getNombre(),
                x.getDescripcion(),
                EstadoBien.valueOf(x.getEstado()) ,
                x.getCategoria().getId(),
                        x.getIdCreador()
        )).toList();
    }

    @Override
    public Bien save(Bien bien) {
        BienEntity entity = new BienEntity(
                bien.getId(),
                bien.getNombre(),
                bien.getDescripcion(),
                bien.getEstado().name(),
                LocalDate.now(),
                LocalTime.now(),
                bien.getPersonaCrea(),
                new CategoriaEntity(bien.getCategoria())
        );

        BienEntity saved = jpaBienRepository.save(entity);

        return Bien.hidratar(
                saved.getId(),
                saved.getNombre(),
                saved.getDescripcion(),
                EstadoBien.valueOf(saved.getEstado()),
                saved.getCategoria().getId(),
                saved.getIdCreador()
        );
    }

    @Override
    public void save(List<Bien> biens) {

        var entities = biens.stream().map(bien->new BienEntity(
                bien.getId(),
                bien.getNombre(),
                bien.getDescripcion(),
                bien.getEstado().name(),
                LocalDate.now(),
                LocalTime.now(),
                bien.getPersonaCrea(),
                new CategoriaEntity(bien.getCategoria())
        )).toList();

        jpaBienRepository.saveAll(entities);
    }

}
