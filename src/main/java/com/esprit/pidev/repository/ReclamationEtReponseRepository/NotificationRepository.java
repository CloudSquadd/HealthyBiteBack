package com.esprit.pidev.repository.ReclamationEtReponseRepository;

import com.esprit.pidev.entities.ReclamationEtReponse.Notification;
import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
