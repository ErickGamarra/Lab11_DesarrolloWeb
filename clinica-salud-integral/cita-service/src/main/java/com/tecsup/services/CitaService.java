package com.tecsup.services;

import com.tecsup.client.MedicoClient;
import com.tecsup.client.PacienteClient;
import com.tecsup.dto.CitaDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Atencion;
import com.tecsup.model.Cita;
import com.tecsup.repository.AtencionRepository;
import com.tecsup.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CitaService {

    private static final Set<String> ESTADOS =
            Set.of("PROGRAMADA", "CONFIRMADA", "ATENDIDA", "CANCELADA");

    @Autowired
    private CitaRepository repo;

    @Autowired
    private AtencionRepository atencionRepo;

    @Autowired
    private PacienteClient pacienteClient;

    @Autowired
    private MedicoClient medicoClient;

    public List<Cita> listar() {
        return repo.findAll();
    }

    public Cita obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cita no encontrada con id: " + id));
    }

    public Cita registrar(CitaDTO dto) {
        pacienteClient.verificar(dto.getIdPaciente());
        medicoClient.verificar(dto.getIdMedico());

        Cita c = new Cita();
        c.setFecha(dto.getFecha());
        c.setHora(dto.getHora());
        c.setIdPaciente(dto.getIdPaciente());
        c.setIdMedico(dto.getIdMedico());
        c.setMotivo(dto.getMotivo());
        return repo.save(c);
    }

    public Cita actualizarEstado(Integer id, String estado) {
        Cita c = obtener(id);
        if (!ESTADOS.contains(estado)) {
            throw new IllegalArgumentException("Estado no valido: " + estado);
        }
        if ("CANCELADA".equals(c.getEstado())) {
            throw new IllegalArgumentException("La cita ya fue cancelada");
        }
        c.setEstado(estado);
        return repo.save(c);
    }

    public Cita cancelar(Integer id) {
        return actualizarEstado(id, "CANCELADA");
    }

    public Atencion registrarAtencion(Integer id, Atencion atencion) {
        Cita c = actualizarEstado(id, "ATENDIDA");
        atencion.setIdCita(c.getIdCita());
        return atencionRepo.save(atencion);
    }
}
