package com.veterinaria.tailvet.repository;

import com.veterinaria.tailvet.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Veterinario findByEmail(String email);
}
