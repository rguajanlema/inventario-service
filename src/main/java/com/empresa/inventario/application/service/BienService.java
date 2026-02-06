package com.empresa.inventario.application.service;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.response.BienResponse;
import com.empresa.inventario.application.port.out.IBienRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService implements IBienService {

    private final IBienRepository bienRepository;

    public BienService(IBienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }


    @Override
    public List<BienResponse> listar() {
        var result = bienRepository.findAll();

        return result.stream().map(x->new BienResponse(
                x.getId(),
                x.getNombre(),
                x.getEstado(),
                x.getCategoria()
        )).toList();
    }


    @Override
    public List<BienResponse> listarDisponibles() {
        var result = bienRepository.findAll();

        return result.stream().map(x->new BienResponse(
                x.getId(),
                x.getNombre(),
                x.getEstado(),
                x.getCategoria()
        )).toList();
    }
}
