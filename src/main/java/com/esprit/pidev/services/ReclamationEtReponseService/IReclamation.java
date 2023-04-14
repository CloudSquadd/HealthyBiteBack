package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ReclamationEtReponse.Notification;
import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import com.esprit.pidev.entities.UserRole.User;

import java.util.List;

public interface IReclamation {

    Reclamation addReclamation(Reclamation rec , Long id );
    Reclamation updateReclamation(Reclamation rec);
    Reclamation retrieveReclamationById(Long idReclamation);
    List<Reclamation> retrieveAllReclamation();
    List<Reclamation> retrieveAllReclamationByUser(long id);
    List<Reclamation> retrieveReclamation(boolean archived);
    void ArchiverReclamation(Long idReclamation);
    void assignRepasToReclamation(Long idReclamation,Long id);
    void assignProduitToReclamation(Long idReclamation,Long id);
    void assignResponseToReclamation(Long idReclamation, Long idReponseReclamation);
    //void assignNotificationToReclamation(Long idReclamation, Long idNotification);
    public List<Reclamation> recupererReclamationsTrieesParDate();
    //void assignReclamationToUser(Long idReclamation, Long id);



}
