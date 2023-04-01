package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
    Set<Fournisseur> findByAdresse(String adresse);
}
