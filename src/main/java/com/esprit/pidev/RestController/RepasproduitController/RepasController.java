package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.security.services.IUser;
import com.esprit.pidev.services.RepasProduitServices.IRepas;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/test")

public class RepasController {

    IRepas iRepas;
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
   @GetMapping("/repas/search")
    public List<Repas> searchRepasByCategorie(@RequestParam("nom") String nom) {
        return iRepas.rechercherRepasParNom(nom);
    }


}
