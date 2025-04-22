package com.cenfotec.asistencia.asistencia.controller;

import com.cenfotec.asistencia.asistencia.dto.AsistenciaDTO;
import com.cenfotec.asistencia.asistencia.entity.Asistencia;
import com.cenfotec.asistencia.asistencia.service.AsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Asistencia> registrar(@RequestBody AsistenciaDTO dto) {
        try {
            Asistencia asistencia = asistenciaService.registrarAsistencia(
                    dto.getIdEstudiante(),
                    dto.getIdClase(),
                    dto.getPresente(),
                    dto.getObservaciones()
            );
            return ResponseEntity.ok(asistencia);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/clase/{idClase}")
    public ResponseEntity<List<Asistencia>> listarPorClase(@PathVariable Long idClase) {
        List<Asistencia> lista = asistenciaService.listarPorClase(idClase);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        return ResponseEntity.ok(asistenciaService.listarTodas());
    }
}