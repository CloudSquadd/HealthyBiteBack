package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.repository.RepasproduitRepository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProduitService implements IProduit{

    ProduitRepository produitRepository;

    @Override
    public Produit addProduit(Produit pr) {
        return produitRepository.save(pr);
    }

    @Override
    public Produit updateProduit(Produit pr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the authorities (roles) of the user
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_FOURNISSEUR".equals(authority.getAuthority())) {
                    // User has "fournisseur" role, check if they are the owner of the produit
                    Long loggedInUserId = Long.parseLong(authentication.getName()); // Get the ID of the currently logged-in user
                    Produit produit = produitRepository.findById(pr.getId()).orElse(null); // Get the produit by ID
                    if (produit != null && produit.getUser().getId().equals(loggedInUserId)) {
                        return produitRepository.save(pr); // User is the owner, allow update
                    }
                    break;
                }
            }
        }
        return pr;


    }

  
    }
    @Override
    public Produit retrieveProduitById(Long id) {

        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> retrieveAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public void deleteProduit(Produit pr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // Get the authorities (roles) of the user
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_Fournisseur".equals(authority.getAuthority())) {
                    // User has "restaurant" role, check if they are the owner of the meal
                    Long loggedInUserId = Long.parseLong(authentication.getName()); // Get the ID of the currently logged-in user
                    Produit produit = produitRepository.findById(pr.getId()).orElse(null); // Get the repas by ID
                    if (produit != null && produit.getUser().getId().equals(loggedInUserId)) {
                        produitRepository.deleteById(pr.getId()); // User is the owner, allow delete
                    }
                    break;
                }
            }
        }
    }

    @Override
    public Set<Produit> getProduitByUserId(Long id) {
        return produitRepository.findByUserId(id);
    }


}
