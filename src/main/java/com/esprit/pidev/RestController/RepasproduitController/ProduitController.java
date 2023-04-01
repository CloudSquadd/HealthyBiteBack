package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.services.RepasProduitServices.IProduit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class ProduitController {

    IProduit iProduit;

    @PostMapping("/addProduit")
    public Produit addProduit(@RequestBody Produit pr){
        return iProduit.addProduit(pr);

    }
    @PutMapping("/updateProduit")
    public Produit updateProduit(@RequestBody Produit pr){
        return iProduit.updateProduit(pr);
    }
    @GetMapping("getProduitById/{id}")
    public Produit retrieveProduitById(@PathVariable("id") Long id){
        return iProduit.retrieveProduitById(id);
    }

    @GetMapping("/getAllProduit")
    public List<Produit> retrieveAllProduit(){
        return iProduit.retrieveAllProduit();
    }
    @DeleteMapping("deleteProduit/{id}")
    public void deleteProduit(@PathVariable("id") Long id){
        iProduit.deleteProduit(id);
    }


    @GetMapping("/getProduitByFournisseurId/{id}")
    public Set<Produit> getProduitByFournisseurId(@PathVariable("id") Long id) {
        return iProduit.getProduitByFournisseurId(id);
    }
}
