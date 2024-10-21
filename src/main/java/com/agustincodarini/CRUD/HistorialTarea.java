package com.agustincodarini.CRUD;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HistorialTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    private String tarea;
    private LocalDate fecha;

    // Getters
    public Long getId() {
        return id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public String getTarea() {
        return tarea;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "HistorialTarea{" +
                "id=" + id +
                ", colaborador=" + colaborador +
                ", tarea='" + tarea + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}


