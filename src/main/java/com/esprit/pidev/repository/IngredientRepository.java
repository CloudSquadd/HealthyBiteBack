package com.esprit.pidev.repository;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
