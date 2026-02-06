package com.empresa.inventario.api.controller;

import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.ApiResponse;
import com.empresa.inventario.application.dto.response.CategoriaCantidadBienesResponse;
import com.empresa.inventario.application.dto.response.CategoriaResponse;
import com.empresa.inventario.application.service.ICategoriaService;
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

    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaCrearCommand command) {
        return ResponseEntity.ok(categoriaService.crearCategoria(command));
    }
    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse> actualizar(@RequestBody CategoriaActualizarCommand command) {
        categoriaService.actualizarCategoria(command);
        return ResponseEntity.ok(
                new ApiResponse("Categoría actualizada correctamente")
        );
    }

    @GetMapping("/listado")
    public ResponseEntity<List<CategoriaResponse>> obtenerTodo() {
        var response = categoriaService.listar();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/bien-disponibles-categoria")
    public ResponseEntity<List<CategoriaCantidadBienesResponse>> obtenerBienDisponiblesCategoria() {
        var response = categoriaService.ObtenerDisponibilidadPorCategoria();
        return ResponseEntity.ok(response);
    }
}
