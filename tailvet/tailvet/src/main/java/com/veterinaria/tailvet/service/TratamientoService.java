package com.veterinaria.tailvet.service;

import com.veterinaria.tailvet.model.Tratamiento;
import com.veterinaria.tailvet.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    // Obtener todos los tratamientos
    public List<Tratamiento> obtenerTodosLosTratamientos() {
        return tratamientoRepository.findAll();
    }

    // Guardar un nuevo tratamiento
    public Tratamiento guardarTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    // Obtener un tratamiento por ID
    public Tratamiento obtenerTratamientoPorId(Long id) {
        Optional<Tratamiento> tratamientoOpt = tratamientoRepository.findById(id);
        return tratamientoOpt.orElse(null);  // Devuelve null si no se encuentra el tratamiento
    }

    // Actualizar un tratamiento
    public Tratamiento actualizarTratamiento(Long id, Tratamiento tratamiento) {
        if (tratamientoRepository.existsById(id)) {
            tratamiento.setId(id);  // Asegúrate de que el ID del tratamiento sea correcto
            return tratamientoRepository.save(tratamiento);
        }
        return null;  // Devuelve null si no existe el tratamiento
    }

    // Eliminar un tratamiento
    public boolean eliminarTratamiento(Long id) {
        if (tratamientoRepository.existsById(id)) {
            tratamientoRepository.deleteById(id);
            return true;  // Devuelve true si se eliminó correctamente
        }
        return false;  // Devuelve false si no existe el tratamiento
    }
}
