package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.BienResponse;
import com.empresa.inventario.application.dto.response.CategoriaCantidadBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaConBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaResponse;
import com.empresa.inventario.application.port.out.IBienRepository;
import com.empresa.inventario.application.port.out.ICategoriaRepository;
import com.empresa.inventario.domain.exception.DomainException;
import com.empresa.inventario.domain.model.Bien;
import com.empresa.inventario.domain.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;
    private final IBienRepository bienRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository, IBienRepository bienRepository) {
        this.categoriaRepository = categoriaRepository;
        this.bienRepository = bienRepository;
    }

    @Override
    public CategoriaResponse crearCategoria(CategoriaCrearCommand request) {
        var consulta = categoriaRepository.findById(request.codigo());

        if(consulta.isPresent()) {
         throw new DomainException("Categoria existente, no se puede registrar porque ya existe");
        }

        var result = categoriaRepository.save(Categoria.crear(request.codigo(), request.nombre(), request.creadoPor()));
        return new CategoriaResponse(result.getId().value(), result.getNombre(),result.getEstado());
    }

    @Override
    public void actualizarCategoria(CategoriaActualizarCommand request) {
        var categoria = categoriaRepository
                .findById(request.codigo())
                .orElseThrow(() ->
                        new DomainException("La categoría no existe")
                );

        categoria.actualizar(request.nombre());

        categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaResponse> listar() {
        var result = categoriaRepository.findAll();
        return result
                .stream()
                .map(x->new CategoriaResponse(x.getId().value(),x.getNombre(),x.getEstado()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoriaCantidadBienesResponse> ObtenerDisponibilidadPorCategoria() {
        var result = categoriaRepository.findDisponibles();
        return result;
    }

    @Override
    public List<CategoriaConBienesResponse> findAllWithBienes() {
        return categoriaRepository.findAllWithBienes();
    }

    @Override
    public long cantidadBienesDisponibles(String id) {
        return categoriaRepository.contarBienesDisponibles(id);
    }

    @Override
    public long cantidadBienesDeBaja(String id) {
        return categoriaRepository.contarBienesDeBaja(id);
    }

    @Override
    public BienResponse crearBien(BienCrearCommand request) {

        categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new DomainException("La categoría no existe"));

        Bien bien = Bien.crear(
                request.nombre(),
                request.descripcion(),
                request.categoriaId(),
                request.personaCrea()
        );

        Bien result = bienRepository.save(bien);

        return new BienResponse(
                result.getId(),
                result.getNombre(),
                result.getEstado(),
                result.getCategoria()
        );
    }


}
