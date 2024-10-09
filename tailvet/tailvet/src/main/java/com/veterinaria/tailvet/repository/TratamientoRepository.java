package com.veterinaria.tailvet.repository;

import com.veterinaria.tailvet.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    
}
