package com.veterinaria.tailvet.controller;
import com.veterinaria.tailvet.model.Tratamiento;
import com.veterinaria.tailvet.service.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")

@RestController  // Cambiado de @Controller a @RestController
@RequestMapping("/api/tratamientos")  // Ruta base para la API de tratamientos

public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    // Obtener la lista de tratamientos
    @GetMapping
    public ResponseEntity<List<Tratamiento>> listarTratamientos() {
        List<Tratamiento> tratamientos = tratamientoService.obtenerTodosLosTratamientos();
        return ResponseEntity.ok(tratamientos);  // Devuelve la lista de tratamientos en formato JSON
    }

    // Obtener un tratamiento por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable Long id) {
        Tratamiento tratamiento = tratamientoService.obtenerTratamientoPorId(id);
        if (tratamiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Devuelve 404 si no se encuentra
        }
        return ResponseEntity.ok(tratamiento);  // Devuelve el tratamiento en formato JSON
    }

    // Procesar el formulario para crear un nuevo tratamiento
    @PostMapping
    public ResponseEntity<Tratamiento> guardarTratamiento(@RequestBody Tratamiento tratamiento) {
        Tratamiento nuevoTratamiento = tratamientoService.guardarTratamiento(tratamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTratamiento);  // Devuelve el tratamiento creado
    }

    // Puedes agregar métodos adicionales para actualizar y eliminar tratamientos si es necesario
}
