package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;

import java.util.List;

public interface IRepas {
    Repas addRepas(Repas rep);
    Repas updateRepas(Repas rep);
    Repas retrieveRepasById(Long id);
    List<Repas> retrieveAllRepas();
    void deleteRepas(Long id);
}
