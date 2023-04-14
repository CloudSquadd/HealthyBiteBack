package com.esprit.pidev.repository.ReclamationEtReponseRepository;

import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    List<Reclamation> findByArchivedTrue();
    List<Reclamation> findByArchivedFalse();
    List<Reclamation> findByUserId(Long userId);
    List<Reclamation> findAllByOrderByDateReclamation();
}
