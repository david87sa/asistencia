package com.cenfotec.asistencia.estudiante.entity;

import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String contactoPadre;
    private Date fechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "seccion_id")
    private Seccion seccion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContactoPadre() {
        return contactoPadre;
    }

    public void setContactoPadre(String contactoPadre) {
        this.contactoPadre = contactoPadre;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
}