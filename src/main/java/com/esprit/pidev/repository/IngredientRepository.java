package com.esprit.pidev.repository;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findByRecette(Recette recette);
}
