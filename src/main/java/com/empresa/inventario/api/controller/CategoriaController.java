package com.empresa.inventario.api.controller;

import com.empresa.inventario.application.dto.request.BienCrearCommand;
import com.empresa.inventario.application.dto.request.CategoriaActualizarCommand;
import com.empresa.inventario.application.dto.request.CategoriaCrearCommand;
import com.empresa.inventario.application.dto.response.*;
import com.empresa.inventario.application.service.ICategoriaService;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/categoria-existentes-bien")
    public ResponseEntity<List<CategoriaConBienesResponse>> obtenerCategoriaExistentesBien() {
        var response = categoriaService.findAllWithBienes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cantidad-bien-disponible")
    public ResponseEntity<Long> cantidadBienDisponible(@RequestParam String id) {
        var response = categoriaService.cantidadBienesDisponibles(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/cantidad-bien-baja")
    public ResponseEntity<Long> cantidadBienBaja(@RequestParam String id) {
        var response = categoriaService.cantidadBienesDeBaja(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear-bien")
    public ResponseEntity<BienResponse> crearBien(@RequestBody BienCrearCommand request) {
        return ResponseEntity.ok(categoriaService.crearBien(request));
    }
    @PostMapping("/crear-lote-bienes")
    public ResponseEntity<ApiResponse> crearBien(@RequestBody List<BienCrearCommand> request) {
        categoriaService.crearLoteBien(request);
        return ResponseEntity.ok(
                new ApiResponse("Lote de bienes creado correctamente")
        );
    }

}
