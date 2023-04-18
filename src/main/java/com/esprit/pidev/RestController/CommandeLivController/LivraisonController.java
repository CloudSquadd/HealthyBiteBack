package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.EtatCommande;
import com.esprit.pidev.entities.CommandeLivraison.Livraison;


import com.esprit.pidev.services.CommandeLivraisonServices.LivraisonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class LivraisonController {
    @Autowired
    private LivraisonService livraisonService;

    @PostMapping("/addLivraison")
    public ResponseEntity<Livraison> addLivraison(@RequestBody Livraison livraison) {
        Livraison newLivraison = livraisonService.addLivraison(livraison);
        return new ResponseEntity<>(newLivraison, HttpStatus.CREATED);
    }

    @GetMapping("/getLivraisonById/{id}")
    public ResponseEntity<Livraison> getLivraisonById(@PathVariable("id") Long id) {
        Livraison livraison = livraisonService.getLivraisonById(id);
        return new ResponseEntity<>(livraison, HttpStatus.OK);
    }

    @PutMapping("/updateLivraison")
    public ResponseEntity<Livraison> updateLivraison(@RequestBody Livraison livraison) {
        Livraison updatedLivraison = livraisonService.updateLivraison(livraison);
        return new ResponseEntity<>(updatedLivraison, HttpStatus.OK);
    }

    @DeleteMapping("/deleteLivraisonById/{id}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable("id") Long id) {
        livraisonService.deleteLivraison(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/accepter")
    public ResponseEntity<Livraison> accepterLivraison(@PathVariable("id") Long id) {
        Livraison livraison = livraisonService.getLivraisonById(id);
        livraison.setEtat(EtatCommande.CONFIRMEE);
        Livraison updatedLivraison = livraisonService.updateLivraison(livraison);
        return new ResponseEntity<>(updatedLivraison, HttpStatus.OK);
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<Livraison> refuserLivraison(@PathVariable("id") Long id) {
        Livraison livraison = livraisonService.getLivraisonById(id);
        livraison.setEtat(EtatCommande.ANNULEE);
        Livraison updatedLivraison = livraisonService.updateLivraison(livraison);
        return new ResponseEntity<>(updatedLivraison, HttpStatus.OK);
    }
}