package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.Commande;
import com.esprit.pidev.entities.CommandeLivraison.EtatCommande;
import com.esprit.pidev.services.CommandeLivraisonServices.CommandeService;
import com.esprit.pidev.services.CommandeLivraisonServices.ICommande;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")

public class CommandeController {
    private final CommandeService commandeService;
    @Autowired
    ICommande iCommande;


    @PostMapping("/addCommande")
    public Commande addCommande(@RequestBody Commande commande) {
        commande.setTotal(calculerCommandeTotale(commande));
        return commandeService.addCommande(commande);
    }

    @PutMapping("updateCommande/{id}")
    public void updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        commande.setIdCom(id);
        commandeService.updateCommande(commande);
    }
    @DeleteMapping("deleteCommande/{id}")
    public void deleteCommandeById(@PathVariable Long id) {
        commandeService.deleteCommandeById(id);
    }


    @GetMapping("/getAllCommande")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("getCommandeById/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }
    @GetMapping("/getCommandeByEtat/{etatCommande}")
    public List<Commande> getCommandesByEtatCommande(@PathVariable EtatCommande etatCommande) {
        return commandeService.getCommandesByEtat(etatCommande);
    }

    @PostMapping("commande/total")
    public double calculerCommandeTotale(@RequestBody Commande commande) {
        double total = iCommande.CalculerTolaleCommande(commande);
        return total;


    }
    @DeleteMapping("/commande/clear")
    public void viderPanier(HttpServletRequest request, HttpServletResponse response) {
        commandeService.viderPanier(request, response);
    }

}

