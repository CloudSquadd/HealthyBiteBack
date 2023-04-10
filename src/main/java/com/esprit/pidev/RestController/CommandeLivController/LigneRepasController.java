package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.LigneRepas;
import com.esprit.pidev.services.CommandeLivraisonServices.ILigneRepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lignes-repas")
public class LigneRepasController {

    @Autowired
    private ILigneRepasService ligneRepasService;

    @GetMapping("/getLigneRepasById/{id}")
    public ResponseEntity<LigneRepas> getLigneRepasById(@PathVariable Long id) {
        LigneRepas ligneRepas = ligneRepasService.getLigneRepasById(id);
        if(ligneRepas != null) {
            return ResponseEntity.ok(ligneRepas);
        }
        return ResponseEntity.notFound().build();}


    @PostMapping("/addLigneRepas")
    public ResponseEntity<LigneRepas> addLigneRepas(@RequestBody LigneRepas ligneRepas) {
        LigneRepas newLigneRepas = ligneRepasService.addLigneRepas(ligneRepas);
        if(newLigneRepas != null) {
            return ResponseEntity.ok(newLigneRepas);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/updateLigneRepasById/{id}")
    public ResponseEntity<LigneRepas> updateLigneRepas(@PathVariable Long id, @RequestBody LigneRepas ligneRepas) {
        LigneRepas updatedLigneRepas = ligneRepasService.updateLigneRepas(ligneRepas);
        if(updatedLigneRepas != null) {
            return ResponseEntity.ok(updatedLigneRepas);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/deleteLigneRepasById/{id}")
    public ResponseEntity<Void> deleteLigneRepasById(@PathVariable Long id) {
        ligneRepasService.deleteLigneRepasById(id);
        return ResponseEntity.noContent().build();
    }
}
