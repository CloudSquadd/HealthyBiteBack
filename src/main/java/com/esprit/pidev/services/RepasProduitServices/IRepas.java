package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

public interface IRepas {
    Repas addRepas(Repas rep);
    Repas updateRepas(Repas rep) throws AccessDeniedException;
    Repas retrieveRepasById(Long id);
    List<Repas> retrieveAllRepas();
    void deleteRepas(Repas rep) throws AccessDeniedException;
     Set<Repas> getRepasByUserId();

    void updateRepasBloqueStatus( );
    int calculerCaloriesTotales(List<Repas> repasChoisis);

    long calculerMaxCalories(User user);

    List<Repas> proposerRepasSelonObjectifEtActivite();

     List<Repas> rechercherRepasParNom(String nom);


}
