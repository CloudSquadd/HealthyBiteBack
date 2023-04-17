package com.esprit.pidev.RestController.CommandeLivController;

import com.esprit.pidev.entities.CommandeLivraison.Commande;
import com.esprit.pidev.entities.CommandeLivraison.EtatCommande;
import com.esprit.pidev.services.CommandeLivraisonServices.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test")
public class CommandeController {
    private final CommandeService commandeService;


    @PostMapping("/addCommande")
    public Commande addCommande(@RequestBody Commande commande) {
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

    @GetMapping("/getCommandeByEtat/{etatCommande}")
    public List<Commande> getCommandesByEtatCommande(@PathVariable EtatCommande etatCommande) {
        return commandeService.getCommandesByEtat(etatCommande);
    }
    @GetMapping("/getAllCommande")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("getCommandeById/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }
}