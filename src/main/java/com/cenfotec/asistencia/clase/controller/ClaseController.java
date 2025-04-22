package com.cenfotec.asistencia.clase.controller;

import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.clase.service.ClaseService;
import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import com.cenfotec.asistencia.estudiante.service.EstudianteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;
    private final EstudianteService estudianteService;

    public ClaseController(ClaseService claseService,EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        this.claseService = claseService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Clase> crearClase(
            @RequestParam Long idSeccion,
            @RequestParam Long idDocente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaFin) {

        Clase clase = claseService.crearClase(idSeccion, idDocente, fecha, horaInicio, horaFin);
        return ResponseEntity.ok(clase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtenerPorId(@PathVariable Long id) {
        return claseService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Clase>> listarTodas() {

        return ResponseEntity.ok(claseService.listarTodas());
    }

    @GetMapping("/docente/{idDocente}")
    public ResponseEntity<List<Clase>> listarPorDocente(@PathVariable Long idDocente) {
        return ResponseEntity.ok(claseService.listarPorDocente(idDocente));
    }
    @GetMapping("/{idClase}/estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorClase(@PathVariable Long idClase) {
        // En este ejemplo simple, asumimos que cada estudiante tiene una clase asignada (relación indirecta).
        List<Estudiante> estudiantes = estudianteService.findByClaseId(idClase);
        return ResponseEntity.ok(estudiantes);
    }
}
