package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ConseilRecette.TypeActivite;
import com.esprit.pidev.entities.ProduitRepas.ObjectifType;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;

import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.Lob;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class RepasService implements IRepas {
    UserRepository userRepository;
    RepasRepository repasRepository;


    public User getCurrentUserObjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Repas addRepas(Repas rep) {
        return repasRepository.save(rep);
    }

    @Override
    public Repas updateRepas(Repas rep) throws AccessDeniedException {
        User user = getCurrentUserObjects();

        if (user.getRoles().equals("ROLE_RESTAURANT") && rep.getUser().getId() == user.getId()) {
            repasRepository.save(rep);
        } else {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer ce repas.");
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
    public void deleteRepas(Repas rep) throws AccessDeniedException {
        User user = getCurrentUserObjects();

        if (user.getRoles().equals("ROLE_RESTAURANT") && rep.getUser().getId() == user.getId()) {
            repasRepository.delete(rep);
        } else {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer ce repas.");
        }
    }




    //calculer nombre de calories dans les repas choisie par le client et verifier qu'il n'a pas exceder le maximum des calories dans les repas choisie
    @Override
    public int calculerCaloriesTotales(List<Repas> repasChoisis) {
        double caloriesTotales = 0;
            double maxCalories = calculerMaxCalories(getCurrentUser());
            for (Repas repas : repasChoisis) {
                caloriesTotales += repas.getNutrition().getCalories();
            }
            System.out.println("le nombre totale des calories est : " + caloriesTotales);
            //notifier le client qu'il excede le maxuimum de calories qu'il doit consommer
            if (caloriesTotales > maxCalories) {
                System.out.println("Le total des calories dépasse le maximum autorisé !");
            }

        return (int) caloriesTotales;

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




    @Override
    public List<Repas> proposerRepasSelonObjectifEtActivite() {
        User user = getCurrentUserObjects();
            ObjectifType objectifClient = user.getObjectif();
            TypeActivite typeActiviteClient = user.getActivite();
            double maxCalories = calculerMaxCalories(user);
            // Calculer les calories minimales et maximales en fonction de l'objectif et du type d'activité
            double minCalories = 0;
            double maxCaloriesProposees = 0;

            switch (objectifClient) {
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
            switch (typeActiviteClient) {
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

            // Rechercher les repas qui ont une quantité de calories entre les calories minimales et maximales proposées et
            // qui correspondent à l'objectif du client
            List<Repas> repasProposes = repasRepository.findByCaloriesAndObjectif(minCalories,maxCaloriesProposees,objectifClient);

            return repasProposes;



    }

    @Override
    public Set<Repas> getRepasByUserId() {
        User user = getCurrentUserObjects();

        if (user.getRoles().equals("ROLE_RESTAURANT")) { 
            return repasRepository.findByUserId(user.getId());
        }
        return repasRepository.findByUserId(user.getId());
    }

    }






