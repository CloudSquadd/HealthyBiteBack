package com.esprit.pidev.repository;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ObjectifRepository extends JpaRepository<Objectif,Long> {
}
