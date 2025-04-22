package com.cenfotec.asistencia.estudiante.dto;

import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;

import java.util.Date;

public class EstudianteDTO {
    private Long idEstudiante;
    private String nombre;
    private Date fechaNacimiento;
    private String contactoPadre;
    private SeccionDTO seccion;

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContactoPadre() {
        return contactoPadre;
    }

    public void setContactoPadre(String contactoPadre) {
        this.contactoPadre = contactoPadre;
    }

    public SeccionDTO getSeccion() {
        return seccion;
    }

    public void setSeccion(SeccionDTO seccion) {
        this.seccion = seccion;
    }
}