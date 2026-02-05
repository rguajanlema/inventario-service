package com.empresa.inventario.api.controller;

import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.service.ICategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody CategoriaCrearCommand command) {
        categoriaService.crearCategoria(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
