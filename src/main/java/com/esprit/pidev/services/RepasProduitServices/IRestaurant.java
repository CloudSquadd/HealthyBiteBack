package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Restaurant;

import java.util.List;

public interface IRestaurant {
    Restaurant addRestaurant(Restaurant res);
    Restaurant updateRestaurant(Restaurant res);
    Restaurant retrieveRestaurantById(Long id);
    List<Restaurant> retrieveAllRestaurant();
    void deleteRestaurant(Long id);
}
