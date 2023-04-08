package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Fournisseur;

import java.util.List;

public interface IFournisseur {
    Fournisseur addFournisseur(Fournisseur fr);
    Fournisseur updateFournisseur(Fournisseur fr);
    Fournisseur retrieveFournisseurById(Long id);
    List<Fournisseur> retrieveAllFournisseur();
    void deleteFournisseur(Long id);
}
