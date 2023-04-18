package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RepasService implements IRepas {

    UserRepository userRepository;
    RepasRepository repasRepository;

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
    public Set<Repas> proposerRepas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Obtenir les détails de l'utilisateur connecté
        User user = (User) authentication.getPrincipal();
        String typeActivite = user.getActivite().toString();
        Set<Repas> repasProposes;
        String objectif = String.valueOf(user.getObjectif());

     /*   switch (typeActivite) {
            case "actif":
                switch (objectif) {
                    case "perdre du poids":
                        // Proposition de repas pour un client actif cherchant à perdre du poids
                        repasProposes = proposerRepasPourClientActifPerdrePoids();
                        break;
                    case "prise de masse":
                        // Proposition de repas pour un client actif cherchant à prendre de la masse
                        repasProposes = proposerRepasPourClientActifPriseMasse();
                        break;
                    default:
                        // Logique de proposition de repas par défaut si aucune correspondance trouvée
                        repasProposes = proposerRepasParDefaut();
                        break;
                }
                break;
            case "non actif":
                switch (objectif) {
                    case "perdre du poids":
                        // Proposition de repas pour un client non actif cherchant à perdre du poids
                        repasProposes = proposerRepasPourClientNonActifPerdrePoids();
                        break;
                    case "prise de masse":
                        // Proposition de repas pour un client non actif cherchant à prendre de la masse
                        repasProposes = proposerRepasPourClientNonActifPriseMasse();
                        break;
                    default:
                        // Logique de proposition de repas par défaut si aucune correspondance trouvée
                        repasProposes = proposerRepasParDefaut();
                        break;
                }
                break;
            default:
                // Logique de proposition de repas par défaut si aucune correspondance trouvée
                repasProposes = proposerRepasParDefaut();
                break;
        }
*/

        // Retourner la liste des repas proposés
        //return repasProp;
        return null;
    }

    @Override
    public List<Repas> rechercherRepasParNom(String nom) {

        return repasRepository.findByNomContainingIgnoreCase(nom);

    }
}


