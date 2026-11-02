package com.tecsup.controller;

import com.tecsup.dto.ProductoDTO;
import com.tecsup.model.Producto;
import com.tecsup.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ProductoDTO dto) {

        Producto c = new Producto();
        c.setNombre(dto.getNombre());
        c.setPrecio(dto.getPrecio());
        c.setStock(dto.getStock());
        c.setCategoriaId(dto.getCategoriaId());

        Producto guardado = service.guardar(c);

        return ResponseEntity.status(201).body(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Producto c = service.obtener(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody ProductoDTO dto)
    {
        Producto existente = service.obtener(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(dto.getNombre());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());
        existente.setCategoriaId(dto.getCategoriaId());
        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Producto c = service.obtener(id);
        if (c == null) {
            return ResponseEntity.status(404).body("No existe");
        }
        service.eliminar(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }
}
