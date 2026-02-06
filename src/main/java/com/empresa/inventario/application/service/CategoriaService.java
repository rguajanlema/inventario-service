package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.CategoriaResponse;
import com.empresa.inventario.application.port.out.ICategoriaRepository;
import com.empresa.inventario.domain.model.Categoria;
import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaResponse crearCategoria(CategoriaCrearCommand request) {
        var result = categoriaRepository.save(Categoria.crear(request.codigo(), request.nombre()));
        return new CategoriaResponse(result.getId(), result.getNombre(),result.getEstado());
    }

    @Override
    public void actualizarCategoria(CategoriaActualizarCommand request) {
        //deberia buscar en el repositorio, y devolver el valor
        //var categoria = Categoria.hidratar("C-001","Ejemplo");
        //categoria.actualizar(request.nombre());
    }

    @Override
    public List<CategoriaResponse> listar() {
        var result = categoriaRepository.findAll();
        return result
                .stream()
                .map(x->new CategoriaResponse(x.getId(),x.getNombre(),x.getEstado()))
                .collect(Collectors.toList());
    }


}
