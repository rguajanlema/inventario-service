package com.empresa.inventario.api.controller;

import com.empresa.inventario.application.dto.response.BienResponse;
import com.empresa.inventario.application.service.IBienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bienes")
public class BienController {
    private final IBienService bienService;


    public BienController(IBienService bienService) {
        this.bienService = bienService;
    }


    @GetMapping("/listado")
    public ResponseEntity<List<BienResponse>> obtenerTodo() {
        var response = bienService.listar();
        return ResponseEntity.ok(response);
    }
}
