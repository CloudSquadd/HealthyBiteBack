package com.esprit.pidev.RestController.RepasproduitController;

import com.esprit.pidev.entities.RepasProduit.Restaurant;
import com.esprit.pidev.services.RepasProduitServices.IRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantController {
    IRestaurant iRestaurant;

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant res){
        return iRestaurant.addRestaurant(res);

    }
    @PutMapping("/updateRestaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant res){
        return iRestaurant.updateRestaurant(res);
    }
    @GetMapping("getRestaurantById/{id}")
    public Restaurant retrieveRestaurantById(@PathVariable("id") Long id){
        return iRestaurant.retrieveRestaurantById(id);
    }

    @GetMapping("/getAllRestaurant")
    public List<Restaurant> retrieveAllRestaurant(){
        return iRestaurant.retrieveAllRestaurant();
    }
    @DeleteMapping("deleteRestaurant/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id){
        iRestaurant.deleteRestaurant(id);
    }
}
