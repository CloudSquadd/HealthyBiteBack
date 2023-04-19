package com.esprit.pidev.repository;

import com.esprit.pidev.entities.Conseil;
import com.esprit.pidev.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseilRepository extends JpaRepository<Conseil,Long> {
}
