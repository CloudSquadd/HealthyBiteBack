package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.Commande;
import com.esprit.pidev.entities.CommandeLivraison.EtatCommande;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.repository.CommandeLivraisonRepo.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService implements ICommande {
    private static final String PANIER_COOKIE_NAME = "panier";

    @Autowired
    private CommandeRepository commandeRepository;


    @Override
    public Commande addCommande(Commande commande) {
        commande.setEtatCommande(EtatCommande.EN_ATTENTE);
        return commandeRepository.save(commande);
    }
    @Override
    public Commande updateCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommandeById(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public Commande getCommandeById(Long id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(id);
        return commandeOptional.orElse(null);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Commande> getCommandesByEtat(EtatCommande etatCommande) {
        return commandeRepository.findByEtatCommande(etatCommande);
    }
    @Override
    public double CalculerTolaleCommande(Commande commande) {
        double total = 0.0;
        for (Produit produit : commande.getProduit()) {
            total += produit.getPrix();
        }
        for (Repas repas : commande.getRepas()) {
            total += repas.getPrix();
        }
        return total;

    }
    public void viderPanier(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie panierCookie = findCookieByName(cookies, PANIER_COOKIE_NAME);
        if (panierCookie != null) {
            panierCookie.setMaxAge(0);
            panierCookie.setValue("");
            response.addCookie(panierCookie);
        }
    }
    private Cookie findCookieByName(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}