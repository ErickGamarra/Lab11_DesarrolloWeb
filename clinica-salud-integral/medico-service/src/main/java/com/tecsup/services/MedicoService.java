package com.tecsup.services;

import com.tecsup.dto.MedicoDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Especialidad;
import com.tecsup.model.HorarioMedico;
import com.tecsup.model.Medico;
import com.tecsup.repository.EspecialidadRepository;
import com.tecsup.repository.HorarioMedicoRepository;
import com.tecsup.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private EspecialidadRepository especialidadRepo;

    @Autowired
    private HorarioMedicoRepository horarioRepo;

    public List<Medico> listar() {
        return repo.findAll();
    }

    public Medico obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medico no encontrado con id: " + id));
    }

    public Medico registrar(MedicoDTO dto) {
        Medico m = new Medico();
        copiar(dto, m);
        return repo.save(m);
    }

    public Medico actualizar(Integer id, MedicoDTO dto) {
        Medico m = obtener(id);
        copiar(dto, m);
        return repo.save(m);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepo.findAll();
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad) {
        return especialidadRepo.save(especialidad);
    }

    public List<HorarioMedico> listarHorarios(Integer idMedico) {
        obtener(idMedico);
        return horarioRepo.findByIdMedico(idMedico);
    }

    public HorarioMedico guardarHorario(Integer idMedico, HorarioMedico horario) {
        obtener(idMedico);
        horario.setIdMedico(idMedico);
        return horarioRepo.save(horario);
    }

    private void copiar(MedicoDTO dto, Medico m) {
        Especialidad especialidad = especialidadRepo.findById(dto.getIdEspecialidad()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Especialidad no encontrada con id: " + dto.getIdEspecialidad()));
        m.setCmp(dto.getCmp());
        m.setNombres(dto.getNombres());
        m.setApellidos(dto.getApellidos());
        m.setTelefono(dto.getTelefono());
        m.setCorreo(dto.getCorreo());
        m.setEspecialidad(especialidad);
    }
}
