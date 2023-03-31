package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.ProduitRepas.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
