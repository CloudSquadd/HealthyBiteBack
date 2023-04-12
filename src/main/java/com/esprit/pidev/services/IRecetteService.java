package com.esprit.pidev.services;

import com.esprit.pidev.entities.Recette;

import java.util.List;
import java.util.Optional;

public interface IRecetteService {

    Recette addRecette(Recette recette);
    Recette updateRecette(Long id, Recette recette);
    Recette retrieveRecetteById(Long id);
    List<Recette> retrieveAllRecette();
    void deleteRecette(Long id);
}
