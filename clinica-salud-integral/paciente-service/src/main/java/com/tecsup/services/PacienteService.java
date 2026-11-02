package com.tecsup.services;

import com.tecsup.dto.PacienteDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Paciente;
import com.tecsup.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    public List<Paciente> listar() {
        return repo.findAll();
    }

    public Paciente obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
    }

    public Paciente registrar(PacienteDTO dto) {
        Paciente p = new Paciente();
        copiar(dto, p);
        return repo.save(p);
    }

    public Paciente actualizar(Integer id, PacienteDTO dto) {
        Paciente p = obtener(id);
        copiar(dto, p);
        return repo.save(p);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }

    private void copiar(PacienteDTO dto, Paciente p) {
        p.setDni(dto.getDni());
        p.setNombres(dto.getNombres());
        p.setApellidos(dto.getApellidos());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setSexo(dto.getSexo());
        p.setTelefono(dto.getTelefono());
        p.setDireccion(dto.getDireccion());
        p.setCorreo(dto.getCorreo());
    }
}
