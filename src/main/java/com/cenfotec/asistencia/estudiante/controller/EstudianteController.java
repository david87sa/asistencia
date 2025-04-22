package com.cenfotec.asistencia.estudiante.controller;

import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import com.cenfotec.asistencia.estudiante.service.EstudianteService;
import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }
/*
    @PostMapping("/registrar")
    public ResponseEntity<Estudiante> registrarEstudiante(
            @RequestParam String nombre,
            @RequestParam Long idSeccion) {

        Estudiante estudiante = estudianteService.registrarEstudiante(nombre, idSeccion);
        return ResponseEntity.ok(estudiante);
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> listarTodos(@RequestParam(required = false) Long seccion) {
        if (seccion != null) {
            return ResponseEntity.ok(estudianteService.listarPorSeccion(seccion));
        }
        return ResponseEntity.ok(estudianteService.listarTodos());
    }
/*
    @GetMapping("/{id}/seccion")
    public ResponseEntity<SeccionDTO> obtenerSeccionDelEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerSeccionDelEstudiante(id));
    }
*/

}