package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class ClienteClient {

    private final RestClient rest = RestClient.create("http://localhost:8081");

    public void verificar(Long id) {
        try {
            rest.get().uri("/api/clientes/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
    }
}
