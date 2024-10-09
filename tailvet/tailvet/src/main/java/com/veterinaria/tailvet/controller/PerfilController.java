package com.veterinaria.tailvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import com.veterinaria.tailvet.model.Usuario;
import com.veterinaria.tailvet.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Ajusta según tu frontend
public class PerfilController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/api/perfil")
    public Usuario getPerfil(HttpSession session) {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Si no hay un usuario en la sesión, lanzar un error o devolver null
        if (usuario == null) {
            throw new RuntimeException("Usuario no autenticado");
        }

        // Devolver el usuario actualizado desde la base de datos
        return usuarioRepository.findById(usuario.getId()).orElse(usuario);
    }
}