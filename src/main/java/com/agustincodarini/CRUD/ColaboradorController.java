package com.agustincodarini.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes desde el frontend
@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public List<Colaborador> listarTodos() {
        return colaboradorService.listarTodos();
    }

    @PutMapping("/{id}")
    public Colaborador modificarColaborador(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        colaborador.setId(id); // Asegúrate de establecer el ID
        return colaboradorService.agregarColaborador(colaborador); // Usa el mismo método de agregar para actualizar
    }

    @PostMapping
    public Colaborador agregarColaborador(@RequestBody Colaborador colaborador) {
        return colaboradorService.agregarColaborador(colaborador);
    }

    @DeleteMapping("/{id}")
    public void eliminarColaborador(@PathVariable Long id) {
        colaboradorService.eliminarColaborador(id);
    }
}


