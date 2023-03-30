package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.RepasProduit.Fournisseur;
import com.esprit.pidev.services.RepasProduitServices.FournisseurService;
import com.esprit.pidev.services.RepasProduitServices.IFournisseur;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FournisseurController {

    IFournisseur iFournisseur;

    @PostMapping("/addFournisseur")
    public Fournisseur addFournisseur(@RequestBody Fournisseur fr){
        return iFournisseur.addFournisseur(fr);

    }
    @PutMapping("/updateFournisseur")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur fr){
        return iFournisseur.updateFournisseur(fr);
    }
    @GetMapping("getFournisseurById/{id}")
    public Fournisseur retrieveFournisseurById(@PathVariable("id") Long id){
        return iFournisseur.retrieveFournisseurById(id);
    }
    @GetMapping("/getAllFournisseur")
    public List<Fournisseur> retrieveAllFournisseur(){
        return iFournisseur.retrieveAllFournisseur();
    }
    @DeleteMapping("deleteFournisseur/{id}")
    public void deleteFournisseur(@PathVariable("id") Long id){
        iFournisseur.deleteFournisseur(id);
    }
}
