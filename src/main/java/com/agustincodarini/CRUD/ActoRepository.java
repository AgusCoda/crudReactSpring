package com.agustincodarini.CRUD;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActoRepository extends JpaRepository<Acto, Long> {
    // Puedes definir métodos personalizados aquí si es necesario
    List<Acto> findByFecha(LocalDate fecha);

    List<Acto> findByFechaBetween(LocalDate startDate, LocalDate endDate);
}

