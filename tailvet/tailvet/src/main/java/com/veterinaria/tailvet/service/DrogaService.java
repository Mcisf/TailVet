package com.veterinaria.tailvet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veterinaria.tailvet.model.Droga;
import com.veterinaria.tailvet.repository.DrogaRepository;
import java.util.List;

@Service
public class DrogaService {

    @Autowired
    private DrogaRepository drogaRepository;

    public List<Droga> getAllDrogas() {
        return drogaRepository.findAll();
    }

    public Droga getDrogaById(Long id) {
        return drogaRepository.findById(id).orElse(null);
    }

    public Droga saveDroga(Droga droga) {
        return drogaRepository.save(droga);
    }

    public void guardarDrogas(List<Droga> drogas) {
        drogaRepository.saveAll(drogas);
    }

    public void deleteDroga(Long id) {
        drogaRepository.deleteById(id);
    }

    public void guardarTodas(List<Droga> drogas) {
        drogaRepository.saveAll(drogas);
    }

}
