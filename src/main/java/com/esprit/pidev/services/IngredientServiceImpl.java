package com.esprit.pidev.services;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.exceptions.IngredientNotFoundException;
import com.esprit.pidev.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IIngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> retrieveIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> retrieveAllIngredient() {
        return ingredientRepository.findAll();
    }

    @Override
    public void deleteIngredient(Long id) {
        this.ingredientRepository.deleteById(id);
    }
}
