package com.cenfotec.asistencia.clase.entity;

import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Clase {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    @ManyToOne
    private Docente docente;
    @ManyToOne
    @JoinColumn(name = "seccion_id")
    private Seccion seccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    // getters y setters

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccionId) {
        this.seccion = seccionId;
    }
}