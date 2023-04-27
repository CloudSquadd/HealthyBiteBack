package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ConseilRecette.TypeActivite;
import com.esprit.pidev.entities.ProduitRepas.ObjectifType;
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

    int calculerCaloriesTotales(List<Repas> repasChoisis);

    double calculerMaxCalories(User user);

    List<Repas> proposerRepasSelonObjectifEtActivite();

     List<Repas> rechercherRepasParNom(String nom);

<<<<<<< Updated upstream
=======
    Repas addRepasAndImage(String nom, String description, double prix, String ingredient, String allergene, ObjectifType objectifType, CategRepas categRepas, MultipartFile image)throws IOException;
>>>>>>> Stashed changes

    public List<Repas> getAllRepasAndImage();
}
