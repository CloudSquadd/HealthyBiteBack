package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.LigneProduit;

import java.util.List;


    public interface ILigneProduitService {


        LigneProduit updateLigneProduit(LigneProduit ligneProduit);


        void deleteLigneProduitById(Long id);

        LigneProduit getLigneProduitById(Long id);



       // List<LigneProduit> getLigneProduitsByCommandeId(Long commandeId);

       // List<LigneProduit> getLigneProduitsByProduitId(Long produitId);

        LigneProduit addLigneProduit(LigneProduit ligneProduit);
        List<LigneProduit> getAllLignesProduits();
    }


