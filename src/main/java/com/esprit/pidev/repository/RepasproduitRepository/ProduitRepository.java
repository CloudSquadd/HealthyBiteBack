package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.RepasProduit.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
