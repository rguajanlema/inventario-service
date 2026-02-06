package com.empresa.inventario.api.controller;

import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.CategoriaResponse;
import com.empresa.inventario.application.service.ICategoriaService;
import com.empresa.inventario.infrastructure.persistence.entity.CategoriaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaCrearCommand command) {
        return ResponseEntity.ok(categoriaService.crearCategoria(command));
    }

    @GetMapping("/listado")
    public ResponseEntity<List<CategoriaResponse>> obtenerTodo() {
        var response = categoriaService.listar();
        return ResponseEntity.ok(response);
    }
}
