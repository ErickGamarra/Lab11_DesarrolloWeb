package com.tecsup.controller;

import com.tecsup.dto.CitaDTO;
import com.tecsup.dto.EstadoDTO;
import com.tecsup.model.Atencion;
import com.tecsup.model.Cita;
import com.tecsup.services.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @PostMapping
    public ResponseEntity<Cita> registrar(@Valid @RequestBody CitaDTO dto) {
        return ResponseEntity.status(201).body(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Cita> actualizarEstado(@PathVariable Integer id,
                                                 @Valid @RequestBody EstadoDTO dto) {
        return ResponseEntity.ok(service.actualizarEstado(id, dto.getEstado()));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @PostMapping("/{id}/atencion")
    public ResponseEntity<Atencion> registrarAtencion(@PathVariable Integer id,
                                                      @Valid @RequestBody Atencion atencion) {
        return ResponseEntity.status(201).body(service.registrarAtencion(id, atencion));
    }
}
