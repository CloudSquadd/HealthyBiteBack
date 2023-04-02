package com.esprit.pidev.services;

import com.esprit.pidev.entities.Recette;
import com.esprit.pidev.exceptions.RecetteNotFoundException;
import com.esprit.pidev.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetteServiceImpl implements IRecetteService{

    @Autowired
    private RecetteRepository recetteRepository;

    @Override
    public Recette addRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    @Override
    public Recette updateRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    @Override
    public Optional<Recette> retrieveRecetteById(Long id) {
        return recetteRepository.findById(id);
    }

    @Override
    public List<Recette> retrieveAllRecette() {
        return recetteRepository.findAll();
    }

    @Override
    public void deleteRecette(Long id) {
        recetteRepository.deleteById(id);
    }
}
