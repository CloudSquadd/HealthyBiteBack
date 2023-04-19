package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.repository.query.Param;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

public interface IProduit {
    Produit addProduit(Produit pr);
    Produit updateProduit(Produit pr) throws AccessDeniedException;
    Produit retrieveProduitById(Long id);
    List<Produit> retrieveAllProduit();
    void deleteProduit(Produit pr) throws AccessDeniedException;

    public Set<Produit> getProduitByUserId(Long id);
    //void checkReclamationsByProduit(Long id);
    void updateProduitBloqueStatus();
}
