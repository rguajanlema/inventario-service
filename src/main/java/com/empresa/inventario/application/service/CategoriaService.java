package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.domain.model.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {
    //aqui debe ir la llamada del repositorio

    @Override
    public void crearCategoria(CategoriaCrearCommand request) {
        Categoria.crear(request.codigo(), request.nombre());
    }

    @Override
    public void actualizarCategoria(CategoriaActualizarCommand request) {
        //deberia buscar en el repositorio, y devolver el valor
        var categoria = Categoria.hidratar(1,"Ejemplo");
        categoria.actualizar(request.nombre());
    }


}
