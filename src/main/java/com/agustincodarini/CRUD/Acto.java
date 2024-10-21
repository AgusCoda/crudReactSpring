package com.agustincodarini.CRUD;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actos")
public class Acto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "acto") // Suponiendo que hay una relación bidireccional con la clase Colaborador
    private List<Colaborador> colaboradores;

    @Column(name = "asignacion")
    private String asignacion; // O el tipo correspondiente según tu implementación

    @OneToOne // Si un acto tiene un único ayudante
    @JoinColumn(name = "ayudante_id") // Ajusta esto según tu esquema de base de datos
    private Colaborador ayudante;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public String getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(String asignacion) {
        this.asignacion = asignacion;
    }

    public Colaborador getAyudante() {
        return ayudante;
    }
    
    public void setAyudante(Colaborador ayudante) {
        this.ayudante = ayudante;
    }

    @Override
    public String toString() {
        return "Acto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", asignacion='" + asignacion + '\'' +
                '}';
    }
}


