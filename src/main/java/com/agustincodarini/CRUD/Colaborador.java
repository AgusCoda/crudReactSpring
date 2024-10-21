package com.agustincodarini.CRUD;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String celular;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String genero; // "Femenino" o "Masculino"

    private boolean inactivo;

    private LocalDate fechaInicioAusencia;
    private LocalDate fechaFinAusencia;

    // Getters y Setters

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isInactivo() {
        return inactivo;
    }

    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    public LocalDate getFechaInicioAusencia() {
        return fechaInicioAusencia;
    }

    public void setFechaInicioAusencia(LocalDate fechaInicioAusencia) {
        this.fechaInicioAusencia = fechaInicioAusencia;
    }

    public LocalDate getFechaFinAusencia() {
        return fechaFinAusencia;
    }

    public void setFechaFinAusencia(LocalDate fechaFinAusencia) {
        this.fechaFinAusencia = fechaFinAusencia;
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", genero='" + genero + '\'' +
                ", inactivo=" + inactivo +
                ", fechaInicioAusencia=" + fechaInicioAusencia +
                ", fechaFinAusencia=" + fechaFinAusencia +
                '}';
    }
}



