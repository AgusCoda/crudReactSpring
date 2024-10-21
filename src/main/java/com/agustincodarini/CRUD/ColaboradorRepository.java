package com.agustincodarini.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta anotación le dice a Spring que esta es una interfaz de repositorio
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

