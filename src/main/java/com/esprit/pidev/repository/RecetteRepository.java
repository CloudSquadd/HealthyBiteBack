package com.esprit.pidev.repository;


import com.esprit.pidev.entities.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteRepository extends JpaRepository<Recette,Long> {
}
