package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepasRepository extends JpaRepository<Repas,Long> {
}
