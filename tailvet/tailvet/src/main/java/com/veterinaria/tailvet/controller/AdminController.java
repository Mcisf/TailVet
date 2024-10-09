package com.veterinaria.tailvet.controller;

import com.veterinaria.tailvet.model.Mascota;
import com.veterinaria.tailvet.model.Tratamiento;
import com.veterinaria.tailvet.model.Usuario;
import com.veterinaria.tailvet.model.Veterinario;
import com.veterinaria.tailvet.service.MascotaService;
import com.veterinaria.tailvet.service.TratamientoService;
import com.veterinaria.tailvet.service.UsuarioService;
import com.veterinaria.tailvet.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // Permite solicitudes de cualquier origen
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/veterinarios")
    public List<Veterinario> obtenerTodosLosVeterinarios() {
        return veterinarioService.obtenerVeterinarios();
    }


    @GetMapping("/mascotas")
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaService.obtenerTodasLasMascotas();
    }

    @GetMapping("/tratamientos")
    public List<Tratamiento> obtenerTodosLosTratamientos() {
        return tratamientoService.obtenerTodosLosTratamientos();
    }

    @GetMapping("/mascota/{id}")
    public Mascota obtenerDetallesMascota(@PathVariable("id") Long id) {
        return mascotaService.obtenerMascotaPorId(id);
    }

    @PutMapping("/mascota/{id}")
    public Mascota actualizarMascota(@PathVariable("id") Long id, @RequestBody Mascota mascota) {
        Mascota existingMascota = mascotaService.obtenerMascotaPorId(id);
        if (existingMascota != null) {
            existingMascota.setNombre(mascota.getNombre());
            existingMascota.setEdad(mascota.getEdad());
            existingMascota.setRaza(mascota.getRaza());
            existingMascota.setPeso(mascota.getPeso());
            existingMascota.setEnfermedad(mascota.getEnfermedad());
            return mascotaService.save(existingMascota);
        } else {
            return null; // Devuelve null si no se encuentra la mascota
        }
    }

    @PutMapping("/usuarios/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        return usuarioService.updateUsuario(id, usuarioActualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
