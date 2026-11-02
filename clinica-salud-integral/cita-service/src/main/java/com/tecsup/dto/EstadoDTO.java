package com.tecsup.dto;

import jakarta.validation.constraints.*;

public class EstadoDTO {

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
