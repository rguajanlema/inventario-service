package com.empresa.inventario.infrastructure.persistence.repository;

import com.empresa.inventario.application.dto.response.BienResponse;
import com.empresa.inventario.application.dto.response.CategoriaCantidadBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaConBienesResponse;
import com.empresa.inventario.application.port.out.ICategoriaRepository;
import com.empresa.inventario.domain.enums.EstadoBien;
import com.empresa.inventario.domain.enums.EstadoCategoria;
import com.empresa.inventario.domain.model.Categoria;
import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class CategoriaRepositoryAdapter implements ICategoriaRepository {
    private final ICategoriaJPARepository jpaRepository;

    public CategoriaRepositoryAdapter(ICategoriaJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Categoria> findById(String id) {
        return jpaRepository.findById(id)
                .map(result -> Categoria.hidratar(
                        result.getId(),
                        result.getNombre(),
                        EstadoCategoria.valueOf(result.getEstado()),
                        result.getIdCreador()
                ));
    }

    @Override
    public List<Categoria> findAll() {
        var result = jpaRepository.findAll();

        return result.stream()
                .map(x->Categoria.hidratar(x.getId(), x.getNombre(), EstadoCategoria.valueOf(x.getEstado()), x.getIdCreador()))
                .toList();
    }

    @Override
    public Categoria save(Categoria categoria) {
        var entity = new CategoriaEntity(
                categoria.getId().value(),
                categoria.getNombre(),
                categoria.getEstado().toString(),
                LocalDate.now(),
                LocalTime.now(),
                categoria.getCreadoPor()
        );

        var result = jpaRepository.save(entity);
        return Categoria.hidratar(result.getId(), result.getNombre(), EstadoCategoria.valueOf(result.getEstado()), result.getIdCreador());
    }

    @Override
    public List<CategoriaCantidadBienesResponse> findDisponibles() {

        return jpaRepository.obtenerCantidadBienesPorCategoria()
                .stream()
                .map(v -> new CategoriaCantidadBienesResponse(
                        v.getCategoriaId(),
                        v.getNombreCategoria(),
                        v.getCantidadBienes()
                ))
                .toList();

    }

    @Override
    public List<CategoriaConBienesResponse> findAllWithBienes() {
        return jpaRepository.findAllWithBienes()
                .stream()
                .map(c -> new CategoriaConBienesResponse(
                        c.getId(),
                        c.getNombre(),
                        c.getBienes().stream()
                                .map(b -> new BienResponse(
                                        b.getId(),
                                        b.getNombre(),
                                        EstadoBien.valueOf(b.getEstado()),
                                        b.getDescripcion()
                                ))
                                .toList()
                ))
                .toList();
    }

    @Override
    public Long contarBienesDisponibles(String categoriaId) {
        return jpaRepository.contarBienesPorEstado(categoriaId, EstadoBien.ACTIVO.toString());
    }

    @Override
    public Long contarBienesDeBaja(String categoriaId) {
        return jpaRepository.contarBienesPorEstado(categoriaId, EstadoBien.BAJA.toString());
    }


}
