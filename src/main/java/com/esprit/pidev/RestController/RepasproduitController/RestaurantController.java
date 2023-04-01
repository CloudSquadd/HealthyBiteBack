package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.ProduitRepas.Restaurant;
import com.esprit.pidev.services.RepasProduitServices.IRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class RestaurantController {
    IRestaurant iRestaurant;

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant res) {
        return iRestaurant.addRestaurant(res);

    }

    @PutMapping("/updateRestaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant res) {
        return iRestaurant.updateRestaurant(res);
    }

    @GetMapping("getRestaurantById/{id}")
    public Restaurant retrieveRestaurantById(@PathVariable("id") Long id) {
        return iRestaurant.retrieveRestaurantById(id);
    }

    @GetMapping("/getAllRestaurant")
    public List<Restaurant> retrieveAllRestaurant() {
        return iRestaurant.retrieveAllRestaurant();
    }

    @DeleteMapping("deleteRestaurant/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id) {
        iRestaurant.deleteRestaurant(id);
    }

    @GetMapping("/getRestaurantsByAdresse/{ville}")
    public ResponseEntity<Set<Restaurant>> getRestaurantsByAdresse(@PathVariable String ville) {
        Set<Restaurant> restaurants = iRestaurant.getRestaurantsByAdresse(ville);
        if (restaurants.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(restaurants);
        }
    }

}
