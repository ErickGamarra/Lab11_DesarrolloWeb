package com.tecsup.controller;

import com.tecsup.model.Especialidad;
import com.tecsup.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad especialidad) {
        return ResponseEntity.status(201).body(service.guardarEspecialidad(especialidad));
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() {
        return ResponseEntity.ok(service.listarEspecialidades());
    }
}
