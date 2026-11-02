package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class PacienteClient {

    private final RestClient rest = RestClient.create("http://localhost:8085");

    public void verificar(Integer id) {
        try {
            rest.get().uri("/api/pacientes/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Paciente no encontrado con id: " + id);
        }
    }
}
