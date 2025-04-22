package com.cenfotec.asistencia.clase.dto;

import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;

import java.time.LocalTime;
import java.util.Date;

public class ClaseDTO {
    private Long id;
    private DocenteDTO docenteId;
    private SeccionDTO seccionId;
    private Date fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocenteDTO getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(DocenteDTO docenteId) {
        this.docenteId = docenteId;
    }

    public SeccionDTO getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(SeccionDTO seccionId) {
        this.seccionId = seccionId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
}