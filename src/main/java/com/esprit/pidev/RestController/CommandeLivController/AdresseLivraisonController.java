package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.AdresseLivraison;
import com.esprit.pidev.services.CommandeLivraisonServices.IAdresseLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adresses-livraison")
public class AdresseLivraisonController {

    @Autowired
    private IAdresseLivraisonService adresseLivraisonService;

    @GetMapping
    public List<AdresseLivraison> getAllAdressesLivraison() {
        return adresseLivraisonService.getAllAdressesLivraison();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseLivraison> getAdresseLivraisonById(@PathVariable Long id) {
        AdresseLivraison adresseLivraison = adresseLivraisonService.getAdresseLivraisonById(id);
        if (adresseLivraison != null) {
            return ResponseEntity.ok(adresseLivraison);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AdresseLivraison> createAdresseLivraison(@RequestBody AdresseLivraison adresseLivraison) {
        adresseLivraison = adresseLivraisonService.saveAdresseLivraison(adresseLivraison);
        return ResponseEntity.status(HttpStatus.CREATED).body(adresseLivraison);
    }

    @PutMapping("/{idAdr}")
    public ResponseEntity<AdresseLivraison> updateAdresseLivraison(@RequestBody AdresseLivraison adresseLivraison, @PathVariable Long idAdr) {
        if (!adresseLivraisonService.existAdresseLivraison(idAdr)) {
            return ResponseEntity.notFound().build();
        }
        adresseLivraison.setIdAdr(idAdr);
        adresseLivraison = adresseLivraisonService.updateAdresseLivraison(adresseLivraison);
        return ResponseEntity.ok(adresseLivraison);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresseLivraison(@PathVariable Long id) {
        if (!adresseLivraisonService.existAdresseLivraison(id)) {
            return ResponseEntity.notFound().build();
        }
        adresseLivraisonService.deleteAdresseLivraisonById(id);
        return ResponseEntity.noContent().build();
    }
}
