package com.agustincodarini.CRUD;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActoService {
    
    private final ActoRepository actoRepository; // Asumiendo que tienes un ActoRepository

    public ActoService(ActoRepository actoRepository) {
        this.actoRepository = actoRepository;
    }

    // Método para guardar un acto
    public Acto saveActo(Acto acto) {
        return actoRepository.save(acto);
    }

    // Método para obtener todos los actos
    public List<Acto> getAllActos() {
        return actoRepository.findAll();
    }

    // Método para obtener un acto por su ID
    public Acto getActoById(Long id) {
        return actoRepository.findById(id).orElse(null);
    }

    // Método para eliminar un acto por su ID
    public void deleteActo(Long id) {
        actoRepository.deleteById(id);
    }

    // Método para buscar actos por fecha
    public List<Acto> findActosByFecha(LocalDate fecha) {
        return actoRepository.findByFecha(fecha);
    }

    public List<Acto> getActosByDateRange(LocalDate startDate, LocalDate endDate) {
        return actoRepository.findByFechaBetween(startDate, endDate);
    }
}

