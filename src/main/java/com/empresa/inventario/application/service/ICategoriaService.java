package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.CategoriaCantidadBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaConBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaResponse;


import java.util.List;

public interface ICategoriaService {
    CategoriaResponse crearCategoria(CategoriaCrearCommand request);
    void actualizarCategoria(CategoriaActualizarCommand request);
    List<CategoriaResponse> listar();
    List<CategoriaCantidadBienesResponse> ObtenerDisponibilidadPorCategoria();
    List<CategoriaConBienesResponse> findAllWithBienes();
    long cantidadBienesDisponibles(String id);

}
