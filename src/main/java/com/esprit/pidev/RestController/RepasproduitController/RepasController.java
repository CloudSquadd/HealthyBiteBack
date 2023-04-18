package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ConseilRecette.TypeActivite;
import com.esprit.pidev.entities.ProduitRepas.ObjectifType;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.security.services.IUser;
import com.esprit.pidev.services.RepasProduitServices.IRepas;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/test")
@AllArgsConstructor
@Data

public class RepasController {
    @Autowired
    IRepas iRepas;
    @Autowired
    IUser iUser;

    @PostMapping("/addRepas")
    public Repas addRepas(@RequestBody Repas rep){
        return iRepas.addRepas(rep);

    }
    @PutMapping("/updateRepas")
    public Repas updateRepas(@RequestBody Repas rep){
        return iRepas.updateRepas(rep);
    }

    @GetMapping("getRepasById/{id}")
    public Repas retrieveRepasById(@PathVariable("id") Long id){
        return iRepas.retrieveRepasById(id);
    }

    @GetMapping("/getAllRepas")
    public List<Repas> retrieveAllRepas(){
        return iRepas.retrieveAllRepas();
    }


    @DeleteMapping("/deleteRepas")
    public void deleteRepas(@RequestBody Repas rep){
        iRepas.deleteRepas(rep);

    }


    @GetMapping("/getRepasByUserId/{id}")
    public Set<Repas> getRepasByUserId(@PathVariable("id") Long id) {
        return iRepas.getRepasByUserId(id);

    }

    @PostMapping("/totalCalories")
    public int calculerCaloriesTotales(@RequestBody List<Repas> repasChoisis) {
        return iRepas.calculerCaloriesTotales(repasChoisis);
    }

    @GetMapping("/maxCalories")
    public double calculerMaxCalories(@RequestBody User user) {

        return iRepas.calculerMaxCalories(user);
    }
   @GetMapping("/searchRepas")
    public List<Repas> searchRepasByCategorie(@RequestParam("nom") String nom) {
        return iRepas.rechercherRepasParNom(nom);
    }


    @GetMapping("/proposer")
    public ResponseEntity<List<Repas>> proposerRepasSelonObjectifEtActivite(@RequestParam ObjectifType objectif, @RequestParam TypeActivite typeActivite)
    {
        List<Repas> repasProposes = iRepas.proposerRepasSelonObjectifEtActivite(objectif, typeActivite);
        return ResponseEntity.ok(repasProposes);
    }
/*
    @PostMapping("/checkNewRepas")
    public ResponseEntity<String> checkNewRepas() {
        iRepas.checkNewRepas();
        return ResponseEntity.ok("Vérification des nouveaux repas en cours !");
    }*/
}
