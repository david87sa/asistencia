package com.cenfotec.asistencia.seguridad.controller;

import com.cenfotec.asistencia.clase.service.ClaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class AuthController {

    ClaseService claseService;
    public AuthController(ClaseService claseService){
        this.claseService=claseService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Esto es solo una simulación. Aquí deberías validar contra la base de datos o un servicio de usuarios.

        Long idDocente = claseService.obtenerDocentePorEmail(email).getId();
        return ResponseEntity.ok(Map.of(
                "message", "Inicio de sesión exitoso",
                "idDocente", idDocente
        ));

    }
}