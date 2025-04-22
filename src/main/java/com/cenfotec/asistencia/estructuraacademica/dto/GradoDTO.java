package com.cenfotec.asistencia.estructuraacademica.dto;

public class GradoDTO {
    private Long id;
    private String nombre;
    private NivelDTO nivel;

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

    public NivelDTO getNivel() {
        return nivel;
    }

    public void setNivel(NivelDTO nivel) {
        this.nivel = nivel;
    }
}