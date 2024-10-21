package com.agustincodarini.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsignacionService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private HistorialTareaRepository historialTareaRepository;

    public Map<String, Colaborador> asignarColaboradores(List<String> tareas) {
        Map<String, Colaborador> asignaciones = new HashMap<>();
        List<Colaborador> colaboradores = colaboradorRepository.findAll();

        for (String tarea : tareas) {
            Colaborador colaborador = encontrarColaboradorDisponible(tarea, asignaciones.values(), colaboradores);
            if (colaborador != null) {
                asignaciones.put(tarea, colaborador);
                // Registrar en el historial
                HistorialTarea historial = new HistorialTarea();
                historial.setColaborador(colaborador);
                historial.setTarea(tarea);
                historial.setFecha(LocalDate.now());
                historialTareaRepository.save(historial);
            }
        }

        return asignaciones;
    }

    private Colaborador encontrarColaboradorDisponible(String tarea, Collection<Colaborador> asignados, List<Colaborador> colaboradores) {
        for (Colaborador colaborador : colaboradores) {
            if (!asignados.contains(colaborador) && !haRealizadoTarea(colaborador, tarea)) {
                return colaborador;
            }
        }
        return null; // Manejo de no encontrar un colaborador
    }

    private boolean haRealizadoTarea(Colaborador colaborador, String tarea) {
        List<HistorialTarea> historiales = historialTareaRepository.findByColaborador(colaborador);
        return historiales.stream().anyMatch(h -> h.getTarea().equals(tarea));
    }
}
