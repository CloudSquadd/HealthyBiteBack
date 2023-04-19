package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repasRepository.save(rep);
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
    public void deleteRepas(Repas rep,User user) throws Exception {
        // Vérifier si l'utilisateur a le rôle de restaurant
        /*if (!user.getRole().equals("restaurant")) {
            throw new Exception("vous n'avez pas le rôle de restaurant pour effectuer cette action");
        }*/

        // Vérifier si l'utilisateur est le propriétaire du repas
        if (!rep.getUser().getId().equals(user.getId())) {
            throw new Exception("vous n'etes pas le propriétaire du repas pour effectuer cette action");
        }
            repasRepository.deleteById(rep.getId());
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
    public void updateRepasBloqueStatus() {
        repasRepository.blockRepasWithTooManyReclamations();
    }
    public String checkMealNutrition(Repas repas) {
        // Logique de vérification des valeurs nutritionnelles d'un repas
        // Exemple : vérifier si les calories dépassent la limite recommandée
        if (repas.getNutrition().getCalories() >600 ) {
            return ("Les calories du repas dépassent la limite recommandée.");
        }
        return "";
    }

    @Override
    public double calculerMetabolismeDeBase(User user) {

        double metabolismeDeBase = 0;

        if (user.getGender().equals("Homme")) {
            metabolismeDeBase = 88.362 + (13.397 * user.getPoids()) + (4.799 * user.getTaille()) - (5.677 * user.getAge());
        } 
        else {
            metabolismeDeBase = 447.593 + (9.247 * user.getPoids()) + (3.098 * user.getTaille()) - (4.330 * user.getAge());
        } 
        return metabolismeDeBase;
    }


}
