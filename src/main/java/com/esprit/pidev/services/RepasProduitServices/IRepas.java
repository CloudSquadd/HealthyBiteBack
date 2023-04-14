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
    void deleteRepas(Long id);

     Set<Repas> getRepasByUserId(Long id);

    public void updateRepasBloqueStatus(Long idRepas);
    int calculerCaloriesTotales(List<Repas> repasChoisis);
}
