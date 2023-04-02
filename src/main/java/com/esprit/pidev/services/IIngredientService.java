package com.esprit.pidev.services;

import com.esprit.pidev.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

    Ingredient addIngredient(Ingredient Ingredient);
    Ingredient updateIngredient(Ingredient Ingredient);
    Optional<Ingredient> retrieveIngredientById(Long id);
    List<Ingredient> retrieveAllIngredient();
    void deleteIngredient(Long id);
}
