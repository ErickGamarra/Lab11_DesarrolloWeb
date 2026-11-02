package com.tecsup.controller;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Pedido;
import com.tecsup.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {

        return service.buscarPorId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedido no encontrado con id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido guardar(@Valid @RequestBody Pedido pedido) {
        return service.guardar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedido) {

        return service.actualizar(id, pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return "Pedido eliminado correctamente";
    }
}
