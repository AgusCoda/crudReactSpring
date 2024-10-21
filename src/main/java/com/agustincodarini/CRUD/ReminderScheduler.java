package com.agustincodarini.CRUD;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReminderScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ActoService actoService; // Servicio para acceder a los actos

    @Scheduled(cron = "0 0 10 * * ?") // Cada día a las 10 AM
    public void sendReminders() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthFromNow = today.plusMonths(1);

        // Obtener los actos programados
        List<Acto> actos = actoService.getActosByDateRange(today, oneMonthFromNow);

        for (Acto acto : actos) {
            for (Colaborador colaborador : acto.getColaboradores()) {
                String subject = "Recordatorio: " + acto.getTitulo();
                String body = "Hola " + colaborador.getNombre() + ",\n\n" +
                              "Te recordamos que tienes asignada la tarea: " + acto.getAsignacion() +
                              "\nFecha del acto: " + acto.getFecha() + 
                              "\nAyudante: " + (acto.getAyudante() != null ? acto.getAyudante().getNombre() : "N/A") +
                              "\n\nSaludos.";
                emailService.sendEmail(colaborador.getEmail(), subject, body);
            }
        }
    }
}

