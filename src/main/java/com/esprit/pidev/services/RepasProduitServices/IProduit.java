package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.RepasProduit.Produit;

import java.util.List;

public interface IProduit {
    Produit addProduit(Produit pr);
    Produit updateProduit(Produit pr);
    Produit retrieveProduitById(Long id);
    List<Produit> retrieveAllProduit();
    void deleteProduit(Long id);
}
