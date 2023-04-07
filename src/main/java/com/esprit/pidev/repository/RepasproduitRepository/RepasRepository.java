package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RepasRepository extends JpaRepository<Repas,Long> {
  //  Set<Repas> findByRestaurantId(Long restaurantId);
}
