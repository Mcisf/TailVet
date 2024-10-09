package com.veterinaria.tailvet.service;

import com.veterinaria.tailvet.model.Mascota;
import com.veterinaria.tailvet.model.Veterinario;
import com.veterinaria.tailvet.repository.MascotaRepository;
import com.veterinaria.tailvet.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Autowired
    public MascotaService(MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    // Método para registrar una nueva mascota asociada a un veterinario
    public Mascota registrarMascota(Long idVeterinario, Mascota mascota) {
        // Busca el veterinario por ID
        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElse(null);
        
        // Verifica que el veterinario existe
        if (veterinario != null) {
            // Asigna el veterinario a la mascota
            mascota.setVeterinario(veterinario);
            // Guarda la mascota en el repositorio
            return mascotaRepository.save(mascota);
        }
        
        
        // Lanza una excepción o retorna null si no se encuentra el veterinario
        return null; // Puedes lanzar una excepción si prefieres
    }

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    public Mascota obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
}
