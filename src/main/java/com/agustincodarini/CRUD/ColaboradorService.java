package com.agustincodarini.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public Colaborador agregarColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public void eliminarColaborador(Long id) {
        colaboradorRepository.deleteById(id);
    }

    // Otros métodos como actualizar colaborador o buscar por inactivo
}


