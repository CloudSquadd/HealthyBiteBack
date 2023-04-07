package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;

import java.util.List;
import java.util.Set;

public interface IProduit {
    Produit addProduit(Produit pr);
    Produit updateProduit(Produit pr);
    Produit retrieveProduitById(Long id);
    List<Produit> retrieveAllProduit();
    void deleteProduit(Long id);

   // public Set<Produit> getProduitByFournisseurId(Long id);
}
