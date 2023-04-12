package com.esprit.pidev.services;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Recette;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

    Ingredient addIngredient(Ingredient Ingredient, Long recetteId);
    Ingredient updateIngredient(Long id, Ingredient Ingredient);
    Optional<Ingredient> retrieveIngredientById(Long id);
    List<Ingredient> retrieveAllIngredient();
    void deleteIngredient(Long id);

    List<Ingredient> retrieveIngredientByRecette(Recette recette);
}
