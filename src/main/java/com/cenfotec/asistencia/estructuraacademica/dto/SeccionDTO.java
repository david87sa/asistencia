package com.cenfotec.asistencia.estructuraacademica.dto;

import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;

public class SeccionDTO {
    private Long id;
    private String nombre;
    private GradoDTO grado;

    public SeccionDTO(Long id,String nombre){
        this.id = id;
        this.nombre = nombre;
    }

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

    public GradoDTO getGrado() {
        return grado;
    }

    public void setGrado(GradoDTO grado) {
        this.grado = grado;
    }
}