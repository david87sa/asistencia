package com.cenfotec.asistencia.estudiante.repository;

import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findBySeccionId(Long idSeccion);
}
