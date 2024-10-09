package com.veterinaria.tailvet.controller;

import com.veterinaria.tailvet.model.Veterinario;
import com.veterinaria.tailvet.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios") // Definimos la ruta base para el controlador
@CrossOrigin(origins = "http://localhost:4200") // Ajusta el origen según sea necesario
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    // Obtener todos los veterinarios
    @GetMapping
    public List<Veterinario> obtenerVeterinarios() {
        return veterinarioService.obtenerVeterinarios();
    }

    // Obtener veterinario por ID
    @GetMapping("/{id}")
    public Veterinario verPerfilVeterinario(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.obtenerVeterinarioPorId(id);
        if (veterinario != null) {
            return veterinario;
        }
        // Aquí puedes lanzar una excepción o devolver un mensaje de error adecuado
        throw new RuntimeException("Veterinario no encontrado con ID: " + id);
    }

    // Agregar un nuevo veterinario
    @PostMapping
    public Veterinario agregarVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioService.agregarVeterinario(veterinario);
    }

    // Actualizar veterinario existente
    @PutMapping("/{id}")
    public Veterinario actualizarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        return veterinarioService.actualizarVeterinario(id, veterinario);
    }

    // Eliminar veterinario por ID
    @DeleteMapping("/{id}")
    public void eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
    }
}
