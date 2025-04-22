package com.cenfotec.asistencia.clase.service;

import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.clase.entity.Docente;
import com.cenfotec.asistencia.clase.repository.ClaseRepository;
import com.cenfotec.asistencia.clase.repository.DocenteRepository;
import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;
import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;
import com.cenfotec.asistencia.estructuraacademica.repository.SeccionRepository;
import com.cenfotec.asistencia.estructuraacademica.service.SeccionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final SeccionService seccionService;
    private final DocenteRepository docenteRepository;

    public ClaseService(ClaseRepository claseRepository, SeccionService seccionService, DocenteRepository docenteRepository) {
        this.claseRepository = claseRepository;
        this.seccionService = seccionService;
        this.docenteRepository = docenteRepository;
    }

    public Clase crearClase(Long idSeccion, Long idDocente, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        SeccionDTO seccionOpt = seccionService.obtenerSeccionPorId(idSeccion);
        Optional<Docente> docenteOpt = docenteRepository.findById(idDocente);
        if (docenteOpt.isEmpty()) {
            throw new RuntimeException("Sección no encontrada con ID: " + idSeccion);
        }


        Seccion seccion = new Seccion();
        seccion.setId(seccionOpt.getId());
        seccion.setNombre(seccionOpt.getNombre());
        seccion.setGrado(seccion.getGrado());

        Clase clase = new Clase();
        clase.setSeccion(seccion);
        clase.setDocente(docenteOpt.get());
        clase.setFecha(fecha);
        clase.setHoraInicio(horaInicio);
        clase.setHoraFin(horaFin);

        return claseRepository.save(clase);
    }

    public Optional<Clase> obtenerPorId(Long id) {
        return claseRepository.findById(id);
    }

    public List<Clase> listarTodas() {
        return claseRepository.findAll();
    }

    public List<Clase> listarPorDocente(Long idDocente) {
        List<Clase> todasLasClases = listarTodas();
        return todasLasClases.stream()
                .filter(clase -> clase.getDocente().getId().equals(idDocente))
                .toList();
    }
    public Docente obtenerDocentePorEmail(String email){
        return docenteRepository.findAll().stream()
                .filter(element -> element.getEmail().equals(email))
                .findFirst().orElseThrow(() ->new RuntimeException("No email found"));
    }
}