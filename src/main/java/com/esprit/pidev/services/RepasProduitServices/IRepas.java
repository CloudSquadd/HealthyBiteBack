package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;

import java.util.List;
import java.util.Set;

public interface IRepas {
    Repas addRepas(Repas rep);
    Repas updateRepas(Repas rep);
    Repas retrieveRepasById(Long id);
    List<Repas> retrieveAllRepas();
    void deleteRepas(Repas rep,User user) throws Exception;

     Set<Repas> getRepasByUserId(Long id);

    int calculerCaloriesTotales(List<Repas> repasChoisis);

     String checkMealNutrition(Repas repas);

    double calculerMetabolismeDeBase(User user);


}
