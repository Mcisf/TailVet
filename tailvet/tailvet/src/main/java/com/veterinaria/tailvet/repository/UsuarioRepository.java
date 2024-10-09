package com.veterinaria.tailvet.repository;

import com.veterinaria.tailvet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findByCedula(String cedula);
}



