package com.agustincodarini.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByUsername(String username);
}
