package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;

import java.util.List;

public interface IReclamation {

    Reclamation addReclamation(Reclamation rec);
    Reclamation updateReclamation(Reclamation rec);
    Reclamation retrieveReclamationById(Long idReclamation);
    List<Reclamation> retrieveAllReclamation();
    List<Reclamation> retrieveReclamation(boolean archived);
    void deleteReclamation(Long idReclamation);
    void assignResponseToReclamation(Long idReclamation, Long idReponseReclamation);
    void assignNotificationToReclamation(Long idReclamation, Long idNotification);

}
