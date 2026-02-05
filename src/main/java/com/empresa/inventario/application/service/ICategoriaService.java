package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;

public interface ICategoriaService {
    void crearCategoria(CategoriaCrearCommand request);
    void actualizarCategoria(CategoriaActualizarCommand request);
}
