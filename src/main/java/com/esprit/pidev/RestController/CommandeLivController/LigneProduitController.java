package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.LigneProduit;
import com.esprit.pidev.services.CommandeLivraisonServices.ILigneProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class LigneProduitController {

    @Autowired
    private ILigneProduitService ligneProduitService;

    @GetMapping("getLigneProduitById/{id}")
    public ResponseEntity<LigneProduit> getLigneProduitById(@PathVariable Long id) {
        LigneProduit ligneProduit = ligneProduitService.getLigneProduitById(id);
        if(ligneProduit != null) {
            return ResponseEntity.ok(ligneProduit);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addLigneProduit")
    public ResponseEntity<LigneProduit> addLigneProduit(@RequestBody LigneProduit ligneProduit) {
        LigneProduit newLigneProduit = ligneProduitService.addLigneProduit(ligneProduit);
        if(newLigneProduit != null) {
            return ResponseEntity.ok(newLigneProduit);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("updateLigneProduit/{id}")
    public ResponseEntity<LigneProduit> updateLigneProduit(@PathVariable Long id, @RequestBody LigneProduit ligneProduit) {
        LigneProduit updatedLigneProduit = ligneProduitService.updateLigneProduit(ligneProduit);
        if(updatedLigneProduit != null) {
            return ResponseEntity.ok(updatedLigneProduit);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("deleteLigneProduitById/{id}")
    public ResponseEntity<Void> deleteLigneProduitById(@PathVariable Long id) {
        ligneProduitService.deleteLigneProduitById(id);
        return ResponseEntity.noContent().build();
    }
}

