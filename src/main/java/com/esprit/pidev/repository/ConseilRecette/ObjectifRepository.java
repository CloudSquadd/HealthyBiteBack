package com.esprit.pidev.repository.ConseilRecette;

import com.esprit.pidev.entities.ConseilRecette.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ObjectifRepository extends JpaRepository<Objectif,Long> {
}
