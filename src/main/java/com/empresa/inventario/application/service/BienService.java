package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.response.BienResponse;
import com.empresa.inventario.domain.model.Bien;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService implements IBienService {



    @Override
    public void Crear(BienCrearCommand request) {
        Bien.crear(request.nombre(), request.categoriaId());

    }

    @Override
    public List<BienResponse> listar() {
        return List.of();
    }
}
