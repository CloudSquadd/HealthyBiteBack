package com.esprit.pidev.RestController;

import com.esprit.pidev.entities.Recette;
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
@RequestMapping("/api/recette")

public class RecetteController {
    @Autowired

    private IRecetteService recetteService;

    @PostMapping("/")
    public ResponseEntity<Recette> add (@RequestBody Recette recette){

        Recette saved = recetteService.addRecette(recette);
        return new ResponseEntity<Recette>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Recette> update(@RequestBody Recette recette) {
        Recette updated = recetteService.updateRecette(recette);
        return new ResponseEntity<Recette>(updated, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Recette> findById(@PathVariable(value = "id") Long id) {
        Optional<Recette> recette = recetteService.retrieveRecetteById(id);
        return new ResponseEntity<Recette>(recette.get(), HttpStatus.FOUND);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Collection<Recette>> getAll() {
        Collection<Recette> recettes = recetteService.retrieveAllRecette();
        return new ResponseEntity<Collection<Recette>>(recettes, HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/recette")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", required = true) Long id) {
        recetteService.retrieveRecetteById(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}

