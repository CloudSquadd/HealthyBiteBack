package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.Commande;
import com.esprit.pidev.entities.CommandeLivraison.Coupon;
import com.esprit.pidev.entities.CommandeLivraison.EtatCommande;
import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;

import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.CommandeLivraisonRepo.CommandeRepository;
import com.esprit.pidev.repository.CommandeLivraisonRepo.CouponRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.Date;



import java.util.List;
import java.util.Optional;

@Service
public class CommandeService implements ICommande {
   

    @Autowired
    private CommandeRepository commandeRepository;
    UserRepository userRepository;
    @Autowired
    private CouponRepository couponRepository;


    @Override
    public Commande addCommande(Commande commande) {
        commande.setEtatCommande(EtatCommande.EN_ATTENTE);
        //commande.setUser(getCurrentUser());
        return commandeRepository.save(commande);
    }
    public User getCurrentUser() {

        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
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

        return commandeRepository.findById(id).orElse(null);
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
    public List<Commande> findCommandesByDateAndEtat(Date startDate, Date endDate, EtatCommande etatCommande) {
        return commandeRepository.findByDateCommandeBetweenAndEtatCommande(startDate, endDate, etatCommande);
    }


    //  public Commande applyCoupon(Long commandeId, String couponCode) {

        // Find the Commande entity by ID
    //  Optional<Commande> optionalCommande = commandeRepository.findById(commandeId);
    //   if (!optionalCommande.isPresent()) {
    //         throw new EntityNotFoundException("Commande not found with ID: " + commandeId);
    //    }

    //   // Find the Coupon entity by code
    //    Coupon coupon = couponRepository.findByCode(couponCode)
    //         .orElseThrow(() -> new RuntimeException("Coupon not found"));
    //  if (coupon == null) {
    //     throw new EntityNotFoundException("Coupon not found with code: " + couponCode);
    //  }

        // Apply the coupon to the Commande entity
        //    Commande commande = optionalCommande.get();
    //  Optional<Coupon> couponOptional = couponRepository.findByCode(commande.getCoupon().getCode());
    //  coupon = couponOptional.orElse(null);
    // commande.setCoupon(coupon);

        // Save the updated Commande entity
    //  return commandeRepository.save(commande);
    // }

}



