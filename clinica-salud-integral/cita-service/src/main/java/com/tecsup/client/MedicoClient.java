package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class MedicoClient {

    private final RestClient rest = RestClient.create("http://localhost:8086");

    public void verificar(Integer id) {
        try {
            rest.get().uri("/api/medicos/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Medico no encontrado con id: " + id);
        }
    }
}
