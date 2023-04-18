package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ConseilRecette.TypeActivite;
import com.esprit.pidev.entities.ProduitRepas.ObjectifType;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@AllArgsConstructor
public class RepasService implements IRepas {

    UserRepository userRepository;
    RepasRepository repasRepository;

 //   SimpMessagingTemplate messagingTemplate;



    //    LocalDateTime lastCheckTime = LocalDateTime.now();




    @Override
    public Repas addRepas(Repas rep) {
        return repasRepository.save(rep);
    }

    @Override
    public Repas updateRepas(Repas rep) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the authorities (roles) of the user
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_RESTAURANT".equals(authority.getAuthority())) {
                    // User has "restaurant" role, check if they are the owner of the meal
                    Long loggedInUserId = Long.parseLong(authentication.getName()); // Get the ID of the currently logged-in user
                    Repas meal = repasRepository.findById(rep.getId()).orElse(null); // Get the meal by ID
                    if (meal != null && meal.getUser().getId().equals(loggedInUserId)) {
                        repasRepository.save(rep); // User is the owner, allow update
                    }
                    break;
                }
            }
        }
        return rep;
    }

    @Override
    public Repas retrieveRepasById(Long id) {
        return repasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Repas> retrieveAllRepas() {
        return repasRepository.findAll();
    }

    @Override
    public void deleteRepas(Repas rep) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // Get the authorities (roles) of the user
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_RESTAURANT".equals(authority.getAuthority())) {
                    // User has "restaurant" role, check if they are the owner of the meal
                    Long loggedInUserId = Long.parseLong(authentication.getName()); // Get the ID of the currently logged-in user
                    Repas repas = repasRepository.findById(rep.getId()).orElse(null); // Get the repas by ID
                    if (repas != null && repas.getUser().getId().equals(loggedInUserId)) {
                        repasRepository.delete(rep); // User is the owner, allow delete
                    }
                    break;
                }
            }
        }

    }

    @Override
    public Set<Repas> getRepasByUserId(Long id) {
        return repasRepository.findByUserId(id);
    }

    //calculer nombre de calories dans les repas choisie par le client et verifier qu'il n'a pas exceder le maximum des calories dans les repas choisie
    @Override
    public int calculerCaloriesTotales(List<Repas> repasChoisis) {
        // Obtenir l'objet d'authentification de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            // Caster l'objet principal en tant que Client
            User user = (User) principal;

            double maxCalories = calculerMaxCalories(user);
            int caloriesTotales = 0;
            for (Repas repas : repasChoisis) {
                caloriesTotales += repas.getNutrition().getCalories();
            }
            //notifier le client qu'il excede le maxuimum de calories qu'il doit consommer
            if (caloriesTotales > maxCalories) {
                System.out.println("Le total des calories dépasse le maximum autorisé !");
            }
            return caloriesTotales;
        }
        return 0;
    }


    //calculer le nombre maximum qu'un client doit consommer par jour
    @Override
    public double calculerMaxCalories(User user) {

        double metabolismeDeBase = 0;

        if (user.getGender().equals("Homme")) {
            metabolismeDeBase = 88.362 + (13.397 * user.getPoids()) + (4.799 * user.getTaille()) - (5.677 * user.getAge());
        } else {
            metabolismeDeBase = 447.593 + (9.247 * user.getPoids()) + (3.098 * user.getTaille()) - (4.330 * user.getAge());
        }
        return Math.round(metabolismeDeBase);
    }

    //proposer des repas selon les activités des utilisateur


    @Override
    public List<Repas> rechercherRepasParNom(String nom) {

        return repasRepository.findByNomContainingIgnoreCase(nom);
    }
/*
    @Scheduled(fixedRate = 3600000)
    @Override
    public void checkNewRepas() {
        List<Repas> newRepas = repasRepository.findByDateAjoutIsGreaterThan(lastCheckTime);
        if (!newRepas.isEmpty()) {
            // Send a message to a specific topic using SimpMessagingTemplate
            simpMessagingTemplate.convertAndSend("/topic/newRepasAvailable", "Nouveaux repas disponibles !");
        }
        lastCheckTime = LocalDateTime.now();
    }*/


    @Override
    public List<Repas> proposerRepasSelonObjectifEtActivite(ObjectifType objectif, TypeActivite typeActivite) {
        // Obtenir l'objet d'authentification de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            // Caster l'objet principal en tant que Client
            User client = (User) principal;

            double maxCalories = calculerMaxCalories(client);

            // Calculer les calories minimales et maximales en fonction de l'objectif et du type d'activité
            double minCalories = 0;
            double maxCaloriesProposees = 0;

            switch (objectif) {
                case Perdre_Poids:
                    minCalories = maxCalories * 0.75; // 75% des calories maximales
                    break;
                case Maintenir_Poids:
                    minCalories = maxCalories * 0.9; // 90% des calories maximales
                    break;
                case Prendre_Poids:
                    minCalories = maxCalories * 1.1; // 110% des calories maximales
                    break;
            }

            switch (typeActivite) {
                case ACTIF:
                    maxCaloriesProposees = maxCalories * 1.1; // 110% des calories maximales
                    break;
                case MODEREMENT_ACTIF:
                    maxCaloriesProposees = maxCalories * 1.05; // 105% des calories maximales
                    break;
                case SEDENTAIRE:
                    maxCaloriesProposees = maxCalories; // Les mêmes calories maximales
                    break;
            }

            // Rechercher les repas qui ont une quantité de calories entre les calories minimales et maximales proposées
            List<Repas> repasProposes = repasRepository.findByQuantiteCaloriesBetween(minCalories, maxCaloriesProposees);

            // Filtrer les repas en fonction de l'objectif
            repasProposes = repasProposes.stream()
                    .filter(repas -> repas.getObjectif() == objectif)
                    .collect(Collectors.toList());

            return repasProposes;
        }

        return Collections.emptyList();
    }



}


