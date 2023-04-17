package com.esprit.pidev.services.ConseilRecetteServices;

import com.esprit.pidev.entities.ConseilRecette.Objectif;

import java.util.List;
import java.util.Optional;

public interface IObjectifService {
    Objectif addObjectif(Objectif objectif);
    Objectif updateObjectif(Long id, Objectif objectif);
    Optional<Objectif> retrieveObjectifById(Long id);
    List<Objectif> retrieveAllObjectif();
    void deleteObjectif(Long id);
}
