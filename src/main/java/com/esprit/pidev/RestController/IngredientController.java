package com.esprit.pidev.RestController;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Recette;
import com.esprit.pidev.services.IIngredientService;
import com.esprit.pidev.services.IRecetteService;
import com.esprit.pidev.services.RecetteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ingredient")

public class IngredientController {

    @Autowired

    private IIngredientService ingredientService;

    @PostMapping("/")
    public ResponseEntity<Ingredient> add (@RequestBody Ingredient ingredient){

        Ingredient saved = ingredientService.addIngredient(ingredient);
        return new ResponseEntity<Ingredient>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredient) {
        Ingredient updated = ingredientService.updateIngredient(ingredient);
        return new ResponseEntity<Ingredient>(updated, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable(value = "id") Long id) {
        Optional<Ingredient> ingredient = ingredientService.retrieveIngredientById(id);
        return new ResponseEntity<Ingredient>(ingredient.get(), HttpStatus.FOUND);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Collection<Ingredient>> getAll() {
        Collection<Ingredient> ingredients = ingredientService.retrieveAllIngredient();
        return new ResponseEntity<Collection<Ingredient>>(ingredients, HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/ingredient")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", required = true) Long id) {
        ingredientService.retrieveIngredientById(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
