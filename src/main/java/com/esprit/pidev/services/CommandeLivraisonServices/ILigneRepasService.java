package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.LigneRepas;

import java.util.List;

public interface ILigneRepasService {
    LigneRepas addLigneRepas(LigneRepas ligneRepas);
    LigneRepas updateLigneRepas(LigneRepas ligneRepas);
    void deleteLigneRepasById(Long id);
    LigneRepas getLigneRepasById(Long id);
    List<LigneRepas> getAllLignesRepas();
    List<LigneRepas> getAllLigneRepas();
    LigneRepas save(LigneRepas ligneRepas);
}
