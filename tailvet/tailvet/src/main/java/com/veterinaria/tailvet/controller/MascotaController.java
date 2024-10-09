package com.veterinaria.tailvet.controller;

import com.veterinaria.tailvet.model.Mascota;
import com.veterinaria.tailvet.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    
    @PostMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<Mascota> registrarMascota(@PathVariable Long idVeterinario, @RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.registrarMascota(idVeterinario, mascota);
        if (nuevaMascota != null) {
            return ResponseEntity.ok(nuevaMascota);
        }
        return ResponseEntity.badRequest().build(); // O manejar el error de otra manera
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        List<Mascota> mascotas = mascotaService.obtenerTodasLasMascotas();
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        if (mascota != null) {
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
