package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.services.RepasProduitServices.IProduit;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@RestController
@Data
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
public class ProduitController {

    IProduit iProduit;

    @PostMapping("/addProduit")
    public Produit addProduit(@RequestBody Produit pr){
        return iProduit.addProduit(pr);

    }
    @PutMapping("/updateProduit")
    public Produit updateProduit(@RequestBody Produit pr) throws AccessDeniedException {
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
    @DeleteMapping("deleteProduit")
    public void deleteProduit(@RequestBody Produit pr) throws AccessDeniedException {
        iProduit.deleteProduit(pr);
    }
    @GetMapping("/getProduitByUserId")
    public Set<Produit> getProduitByUserId() {
        return iProduit.getProduitByUserId();
    }
}
