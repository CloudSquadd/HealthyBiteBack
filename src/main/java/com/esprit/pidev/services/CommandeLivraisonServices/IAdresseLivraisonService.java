package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.AdresseLivraison;

import java.util.List;

public interface IAdresseLivraisonService {
    AdresseLivraison saveAdresseLivraison(AdresseLivraison adresseLivraison);
    AdresseLivraison updateAdresseLivraison(AdresseLivraison adresseLivraison);
    void deleteAdresseLivraisonById(Long id);
    AdresseLivraison getAdresseLivraisonById(Long id);
    List<AdresseLivraison> getAllAdressesLivraison();
    boolean existAdresseLivraison(Long idAdr);
}
