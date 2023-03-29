package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.RepasProduit.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
