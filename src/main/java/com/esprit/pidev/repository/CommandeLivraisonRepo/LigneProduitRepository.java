package com.esprit.pidev.repository.CommandeLivraisonRepo;

import com.esprit.pidev.entities.CommandeLivraison.Commande;
import com.esprit.pidev.entities.CommandeLivraison.LigneProduit;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneProduitRepository extends JpaRepository<LigneProduit, Long> {
    List<LigneProduit> findByProduit(Produit produit);
    List<LigneProduit> findByCommande(Commande commande);
    LigneProduit save(LigneProduit ligneProduit);
}
