package com.cenfotec.asistencia.estructuraacademica.controller;

import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;
import com.cenfotec.asistencia.estructuraacademica.service.SeccionService;
import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import com.cenfotec.asistencia.estudiante.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/secciones")
public class SeccionController {

    private final SeccionService seccionService;

    public SeccionController(SeccionService seccionService) {
        this.seccionService = seccionService;
    }

    @GetMapping
    public ResponseEntity<List<SeccionDTO>> listarTodos(@RequestParam(required = false) Long seccion) {
        if (seccion != null) {
            List<SeccionDTO> result = new ArrayList<>();
            result.add(seccionService.obtenerSeccionPorId(seccion));
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(seccionService.listarTodos());
    }

}