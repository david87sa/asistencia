package com.cenfotec.asistencia.asistencia.service;

import com.cenfotec.asistencia.asistencia.entity.Asistencia;
import com.cenfotec.asistencia.asistencia.repository.AsistenciaRepository;
import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import com.cenfotec.asistencia.estudiante.repository.EstudianteRepository;
import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.clase.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepo;
    private final EstudianteRepository estudianteRepo;
    private final ClaseRepository claseRepo;

    public AsistenciaService(AsistenciaRepository asistenciaRepo,
                             EstudianteRepository estudianteRepo,
                             ClaseRepository claseRepo) {
        this.asistenciaRepo = asistenciaRepo;
        this.estudianteRepo = estudianteRepo;
        this.claseRepo = claseRepo;
    }

    public Asistencia registrarAsistencia(Long estudianteId, Long claseId, boolean presente, String observaciones) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + estudianteId));

        Clase clase = claseRepo.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada con ID: " + claseId));

        Asistencia asistencia = new Asistencia();
        asistencia.setEstudiante(estudiante);
        asistencia.setClase(clase);
        asistencia.setPresente(presente);
        asistencia.setObservaciones(observaciones);

        return asistenciaRepo.save(asistencia);
    }

    public Optional<Asistencia> obtenerPorId(Long id) {
        return asistenciaRepo.findById(id);
    }

    public List<Asistencia> listarPorClase(Long claseId) {
        return asistenciaRepo.findByClaseId(claseId);
    }

    public List<Asistencia> listarTodas() {
        return asistenciaRepo.findAll();
    }
}