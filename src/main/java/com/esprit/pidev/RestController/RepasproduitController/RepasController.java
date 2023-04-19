package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.security.services.IUser;
import com.esprit.pidev.services.RepasProduitServices.IRepas;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
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
   /* @DeleteMapping("deleteRepas/{id}")
    public void deleteRepas(@PathVariable("id")  Repas rep, User user){
        iRepas.deleteRepas(rep.getId());
    }*/


    @GetMapping("getRepasByUserId/{id}")
    public Set<Repas> getRepasByUserId(@PathVariable("id") Long id) {
        return iRepas.getRepasByUserId(id);

    }

    @PostMapping("calories/total")
    public int calculerCaloriesTotales(@RequestBody List<Repas> repasChoisis) {
        return iRepas.calculerCaloriesTotales(repasChoisis);
    }

    @GetMapping("metabolisme/{id}")
    public double calculerMaxCalories(@PathVariable("id") Long id) {
        User user = iUser.retrieveUserById(id);
        double metabolismeDeBase = iRepas.calculerMaxCalories(user);
        return metabolismeDeBase;
    }


}
