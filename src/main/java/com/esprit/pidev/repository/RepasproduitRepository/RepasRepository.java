package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.RepasProduit.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepasRepository extends JpaRepository<Repas,Long> {
}
