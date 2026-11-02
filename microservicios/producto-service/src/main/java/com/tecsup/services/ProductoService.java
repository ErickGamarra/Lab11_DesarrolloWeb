package com.tecsup.services;

import com.tecsup.client.CategoriaClient;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto guardar(Producto p) {
        categoriaClient.verificar(p.getCategoriaId());
        return repo.save(p);
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Producto no encontrada con id: " + id
                ));
    }

    public void eliminar(Long id) {
        Producto producto =
                repo.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Producto no encontrada con id: " + id)
                        );
        repo.delete(producto);
    }
}
