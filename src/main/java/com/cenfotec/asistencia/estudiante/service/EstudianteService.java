package com.cenfotec.asistencia.estudiante.service;

import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.clase.service.ClaseService;
import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;
import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import com.cenfotec.asistencia.estudiante.repository.EstudianteRepository;
import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;
import com.cenfotec.asistencia.estructuraacademica.service.SeccionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final ClaseService claseService;


    public EstudianteService(EstudianteRepository estudianteRepository, ClaseService claseService) {
        this.estudianteRepository = estudianteRepository;
        this.claseService = claseService;
    }

/*    public Estudiante registrarEstudiante(String nombre, Long idSeccion) {
        SeccionDTO seccion = seccionService.obtenerSeccionPorId(idSeccion);

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setSeccionId(idSeccion);

        return estudianteRepository.save(estudiante);
    }
*/
    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public List<Estudiante> listarPorSeccion(Long idSeccion) {
        return estudianteRepository.findBySeccionId(idSeccion);
    }

/*
    public SeccionDTO obtenerSeccionDelEstudiante(Long id) {
        Long seccionId = estudianteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Sección no encontrada")).getSeccionId();
        SeccionDTO seccionOpt = seccionService.obtenerSeccionPorId(seccionId);

        return new SeccionDTO(seccionOpt.getId(), seccionOpt.getNombre());

    }
*/
    public List<Estudiante> findByClaseId(Long idClase) {
        Optional<Clase> clase = claseService.obtenerPorId(idClase);
        if (clase.isEmpty()){
            throw new RuntimeException("No se encontro la clase");
        }
        return estudianteRepository.findBySeccionId(clase.get().getId());
    }
}