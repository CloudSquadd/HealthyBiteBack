package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.RepasProduit.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
