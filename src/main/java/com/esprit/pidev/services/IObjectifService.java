package com.esprit.pidev.services;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Objectif;

import java.util.List;
import java.util.Optional;

public interface IObjectifService {
    Objectif addObjectif(Objectif objectif);
    Objectif updateObjectif(Objectif objectif);
    Optional<Objectif> retrieveObjectifById(Long id);
    List<Objectif> retrieveAllObjectif();
    void deleteObjectif(Long id);
}
