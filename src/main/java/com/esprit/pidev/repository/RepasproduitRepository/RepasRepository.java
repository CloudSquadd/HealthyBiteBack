package com.esprit.pidev.repository.RepasproduitRepository;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface RepasRepository extends JpaRepository<Repas,Long> {
    Set<Repas> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Repas r SET r.bloquee = true WHERE r.id IN " +
            "(SELECT rp.id FROM Repas rp JOIN rp.reclamations rc " +
            "GROUP BY rp HAVING COUNT(rc) > 3)")
    void blockRepasWithTooManyReclamations();

}
