package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.services.RepasProduitServices.IRepas;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class RepasController {

    IRepas iRepas;

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
    @DeleteMapping("deleteRepas/{id}")
    public void deleteRepas(@PathVariable("id") Long id){
        iRepas.deleteRepas(id);
    }


    @GetMapping("getRepasByUserId/{id}")
    public Set<Repas> getRepasByUserId(@PathVariable Long id) {
        return iRepas.getRepasByUserId(id);

    }

    @PostMapping("calories/total")
    public int calculerCaloriesTotales(@RequestBody List<Repas> repasChoisis) {
        return iRepas.calculerCaloriesTotales(repasChoisis);
    }


}
