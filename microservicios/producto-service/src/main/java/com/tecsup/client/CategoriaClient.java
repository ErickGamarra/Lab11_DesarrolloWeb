package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class CategoriaClient {

    private final RestClient rest = RestClient.create("http://localhost:8082");

    public void verificar(Long id) {
        try {
            rest.get().uri("/api/categorias/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Categoria no encontrada con id: " + id);
        }
    }
}
