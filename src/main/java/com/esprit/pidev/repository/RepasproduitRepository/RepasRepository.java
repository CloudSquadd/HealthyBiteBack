package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RepasRepository extends JpaRepository<Repas,Long> {
    Set<Repas> findByUserId(Long userId);
    @Modifying
    @Query("UPDATE Repas p SET p.bloquee= CASE WHEN SIZE(p.reclamations) > 2 THEN TRUE ELSE FALSE END WHERE p.id = :idRepas")
    void updateRepasBloqueStatus(@Param("idRepas") Long idRepas);

}
