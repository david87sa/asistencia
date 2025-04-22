package com.cenfotec.asistencia.asistencia.entity;

import com.cenfotec.asistencia.clase.entity.Clase;
import com.cenfotec.asistencia.estudiante.entity.Estudiante;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Asistencia {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Clase clase;

    private String observaciones;

    private boolean presente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String obervaciones) {
        this.observaciones = obervaciones;
    }
}
