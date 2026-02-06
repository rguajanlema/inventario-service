package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.response.BienResponse;


import java.util.List;

public interface IBienService {
    List<BienResponse> listar();
    List<BienResponse> listarDisponibles();
}
