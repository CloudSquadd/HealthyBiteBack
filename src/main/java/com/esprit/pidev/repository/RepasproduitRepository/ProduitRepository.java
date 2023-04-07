package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
   // Set<Produit> findByFournisseurId(Long fournisseurId);
}
