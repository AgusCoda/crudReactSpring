package com.agustincodarini.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/crear")
    public String crearAdministrador(@RequestParam String username, @RequestParam String password) {
        Administrador admin = new Administrador();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password)); // Encriptar la contraseña
        administradorRepository.save(admin);
        return "Administrador creado correctamente.";
    }
}

