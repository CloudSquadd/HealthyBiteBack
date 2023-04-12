package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ReclamationEtReponse.Notification;
import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;

import java.util.List;

public interface INotification {
    Notification addNotification(Notification not);
    Notification updateNotification(Notification not);
    Notification retrieveNotificationById(Long idNotification);
    List<Notification> retrieveAllNotification();
    void deleteNotification(Long idNotification);
}
