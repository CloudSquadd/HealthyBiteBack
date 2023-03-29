package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.RepasProduit.Restaurant;
import com.esprit.pidev.repository.RepasproduitRepository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurant{
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant addRestaurant(Restaurant res) {
        return restaurantRepository.save(res);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant res) {
        return restaurantRepository.save(res);
    }

    @Override
    public Restaurant retrieveRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> retrieveAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public void deleteRestaurant(Long id) {
            restaurantRepository.deleteById(id);
    }
}
