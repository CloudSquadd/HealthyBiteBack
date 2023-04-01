package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.ProduitRepas.Restaurant;

import java.util.List;
import java.util.Set;

public interface IRestaurant {
    Restaurant addRestaurant(Restaurant res);
    Restaurant updateRestaurant(Restaurant res);
    Restaurant retrieveRestaurantById(Long id);
    List<Restaurant> retrieveAllRestaurant();
    void deleteRestaurant(Long id);

     Set<Restaurant> getRestaurantsByAdresse(String ville);




}
