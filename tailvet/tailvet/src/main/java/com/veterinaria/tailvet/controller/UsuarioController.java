package com.veterinaria.tailvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.veterinaria.tailvet.service.UsuarioService;
import com.veterinaria.tailvet.model.*;
import com.veterinaria.tailvet.repository.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Ruta base para este controlador
@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde la aplicación Angular
public class UsuarioController {

    

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository; 

    // Inyección del servicio que maneja la lógica de negocio

    @GetMapping("/usuarios") // Ruta para obtener todos los usuarios
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.findAllUsuarios(); // Llamada al servicio
        return ResponseEntity.ok(usuarios); // Retorna la lista de usuarios con estado HTTP 200
    }

        @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Usuario> obtenerUsuarioPorCedula(@PathVariable String cedula) {
        Usuario usuario = usuarioService.obtenerUsuarioPorCedula(cedula);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
