package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ReclamationEtReponse.Notification;
import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import com.esprit.pidev.entities.ReclamationEtReponse.ReponseReclamation;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.NotificationRepository;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReclamationRepository;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReponseReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReclamationService implements IReclamation {

    ReclamationRepository reclamationRepository;
    ReponseReclamationRepository reponseReclamationRepository;
    NotificationRepository notificationRepository;

    @Override
    public Reclamation addReclamation(Reclamation rec) {
        return reclamationRepository.save(rec);
    }

    @Override
    public Reclamation updateReclamation(Reclamation rec) {
        return reclamationRepository.save(rec);
    }

    @Override
    public Reclamation retrieveReclamationById(Long idReclamation) {
        return reclamationRepository.findById(idReclamation).orElse(null);
    }

    @Override
    public List<Reclamation> retrieveAllReclamation() {
        return reclamationRepository.findAll();
    }

    @Override
    public List<Reclamation> retrieveReclamation(boolean archived) {
        return reclamationRepository.findByArchivedFalse();
    }

    // @Override
    //public void deleteReclamation(Long idReclamation) {
     //   reclamationRepository.deleteById(idReclamation);
    //}
    @Override
    public void assignResponseToReclamation(Long idReclamation, Long idReponseReclamation) {
        Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
        ReponseReclamation rep =reponseReclamationRepository.findById(idReponseReclamation).orElse(null);
        rec.setReponseReclamation(rep);
        reclamationRepository.save(rec);
    }

    @Override
    public void assignNotificationToReclamation(Long idReclamation, Long idNotification) {
        Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
        Notification not =notificationRepository.findById(idNotification).orElse(null);
        rec.setNotifications(not);
        reclamationRepository.save(rec);
    }
    public void deleteReclamation(Long idReclamation) {
        Reclamation reclamation = reclamationRepository.findById(idReclamation)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        reclamation.setArchived(true);
        reclamationRepository.save(reclamation);
    }

}
