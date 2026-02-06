package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.response.BienResponse;


import java.util.List;

public interface IBienService {
    //BienResponse crear(BienCrearCommand request);
    List<BienResponse> listar();
    List<BienResponse> listarDisponibles();
}
