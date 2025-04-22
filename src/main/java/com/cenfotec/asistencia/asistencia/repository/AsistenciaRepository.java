package com.cenfotec.asistencia.asistencia.repository;

import com.cenfotec.asistencia.asistencia.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByClaseId(Long claseId);
}
