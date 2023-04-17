package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RepasService implements IRepas{

    RepasRepository repasRepository;

    @Override
    public Repas addRepas(Repas rep) {
        return repasRepository.save(rep);
    }
    @Override
    public Repas updateRepas(Repas rep){
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
    public void deleteRepas(Repas rep){
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

    @Override
    public int calculerCaloriesTotales(List<Repas> repasChoisis) {
        int caloriesTotales = 0;
        for (Repas repas : repasChoisis) {
            caloriesTotales += repas.getNutrition().getCalories();
        }
        return caloriesTotales;
    }

    @Override
    public String checkMealNutrition(Repas repas) {
        // Logique de vérification des valeurs nutritionnelles d'un repas
        // Exemple : vérifier si les calories dépassent la limite recommandée
        if (repas.getNutrition().getCalories() >600 ) {
            return ("Les calories du repas dépassent la limite recommandée.");
        }
        return "";
    }



    @Override
    public double calculerMaxCalories(User user) {

        double metabolismeDeBase = 0;

        if (user.getGender().equals("Homme")) {
            metabolismeDeBase = 88.362 + (13.397 * user.getPoids()) + (4.799 * user.getTaille()) - (5.677 * user.getAge());
        }
        else {
            metabolismeDeBase = 447.593 + (9.247 * user.getPoids()) + (3.098 * user.getTaille()) - (4.330 * user.getAge());
        }
        return Math.round(metabolismeDeBase);
    }


}
