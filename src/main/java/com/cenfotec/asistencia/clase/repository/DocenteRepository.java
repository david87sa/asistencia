package com.cenfotec.asistencia.clase.repository;

import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.clase.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {}

