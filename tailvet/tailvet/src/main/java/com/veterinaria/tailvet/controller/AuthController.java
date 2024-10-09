package com.veterinaria.tailvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinaria.tailvet.model.Usuario;
import com.veterinaria.tailvet.model.Veterinario;
import com.veterinaria.tailvet.repository.UsuarioRepository;
import com.veterinaria.tailvet.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeterinarioService veterinarioService;

    public static class LoginRequest {
        public String cedula;
        public String email;
        public String password;
        // Getters y Setters
    }

    public static class LoginClienteRequest {
        public String cedula;
    }



    public static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    @PostMapping("/login-cliente")
    public ResponseEntity<?> loginCliente(@RequestBody LoginClienteRequest request, HttpSession session) {
        Usuario cliente = usuarioRepository.findByCedula(request.cedula);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Cédula no encontrada."));
        }
        session.setAttribute("usuario", cliente);
        return ResponseEntity.ok(cliente);
    }
    


    @PostMapping("/login-veterinario")
    public ResponseEntity<?> loginVeterinario(@RequestBody LoginRequest request, HttpSession session) {
        Veterinario veterinario = veterinarioService.obtenerVeterinarioPorEmail(request.email);
        if (veterinario == null || !veterinario.getPassword().equals(request.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Correo o contraseña incorrectos."));
        }
        session.setAttribute("usuario", veterinario);
        return ResponseEntity.ok(veterinario);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada con éxito.");
    }
}
