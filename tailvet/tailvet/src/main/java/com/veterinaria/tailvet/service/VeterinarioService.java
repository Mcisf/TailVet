package com.veterinaria.tailvet.service;

import com.veterinaria.tailvet.model.Veterinario;
import com.veterinaria.tailvet.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Obtener veterinario por ID
    public Veterinario obtenerVeterinarioPorId(Long id) {
        return veterinarioRepository.findById(id).orElse(null);
    }

    // Obtener todos los veterinarios
    public List<Veterinario> obtenerVeterinarios() {
        return veterinarioRepository.findAll();
    }


    // Obtener veterinario por email
    public Veterinario obtenerVeterinarioPorEmail(String email) {
        return veterinarioRepository.findByEmail(email);
    }

    // Agregar un nuevo veterinario
    public Veterinario agregarVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    // Actualizar veterinario existente
    public Veterinario actualizarVeterinario(Long id, Veterinario veterinario) {
        if (veterinarioRepository.existsById(id)) {
            veterinario.setId(id); // Asegúrate de que el ID sea el correcto
            return veterinarioRepository.save(veterinario);
        }
        return null; // O lanzar una excepción si prefieres
    }

    // Eliminar veterinario por ID
    public void eliminarVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }
}
