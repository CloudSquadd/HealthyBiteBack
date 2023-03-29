package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.RepasProduit.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository extends JpaRepository<Nutrition,Long> {
}
