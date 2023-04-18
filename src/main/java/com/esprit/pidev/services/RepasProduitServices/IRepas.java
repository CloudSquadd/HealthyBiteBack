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


    void deleteRepas(Repas rep);


     Set<Repas> getRepasByUserId(Long id);

    int calculerCaloriesTotales(List<Repas> repasChoisis);


    double calculerMaxCalories(User user);

    Set<Repas> proposerRepas();

    //Set<Repas> searchRepasByNom(String nom);
     List<Repas> rechercherRepasParNom(String nom);


}
