package com.veterinaria.tailvet.controller;

import com.veterinaria.tailvet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";  // Nombre del archivo HTML en templates
    }

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro") // Añadido el path aquí
    public String registrarUsuario(
        @RequestParam String nombre,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String direccion,
        @RequestParam String telefono,
        @RequestParam int edad,
        @RequestParam double peso,
        @RequestParam String imagenUrl) {

        boolean exito = usuarioService.registrarUsuario(nombre, email, password, direccion, telefono, edad, peso, imagenUrl);

        if (exito) {
            // Redirigir a la página de inicio de sesión
            return "redirect:/usuario-login";
        } else {
            // Redirigir a la página de registro con un parámetro de error
            return "redirect:/registro?error";
        }
    }
}
