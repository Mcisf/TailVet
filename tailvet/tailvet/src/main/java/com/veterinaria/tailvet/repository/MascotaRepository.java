package com.veterinaria.tailvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.veterinaria.tailvet.model.Mascota;
import com.veterinaria.tailvet.model.Usuario;
import com.veterinaria.tailvet.model.Veterinario;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByUsuario(Usuario usuario);
    List<Mascota> findByVeterinarioId(Veterinario veterinarioId);
    List<Mascota> findByVeterinarioId(Long idVeterinario);
}
