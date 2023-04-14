package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.ReclamationEtReponse.Notification;
import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import com.esprit.pidev.entities.ReclamationEtReponse.ReponseReclamation;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.NotificationRepository;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReclamationRepository;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReponseReclamationRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import com.esprit.pidev.services.RepasProduitServices.ProduitService;
import com.esprit.pidev.services.RepasProduitServices.RepasService;
import com.esprit.pidev.services.UserRoleService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReclamationService implements IReclamation {

    ReclamationRepository reclamationRepository;
    INotification notificationService;
    ReponseReclamationRepository reponseReclamationRepository;
    UserService userService;
    RepasService repasService;
    ProduitService produitService;


    @Override
    public void assignRepasToReclamation(Long idReclamation,Long id){
        Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
        Repas repas = repasService.retrieveRepasById(id);
        rec.setRepas(repas);
        reclamationRepository.save(rec);
    }

    @Override
    public void assignProduitToReclamation(Long idReclamation,Long id) {
        Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
        Produit produit = produitService.retrieveProduitById(id);
        rec.setProduit(produit);
        reclamationRepository.save(rec);
    }

    @Override
    public Reclamation addReclamation(Reclamation rec, Long id) {
        User user = userService.retrieveUserById(id);
        rec.setUser(user);
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
    public List<Reclamation> retrieveAllReclamationByUser(long id) {
        return reclamationRepository.findByUserId(id);
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
        Notification not = new Notification();
        not.setTextNotification("Votre Reclamation a ete traitee");
        not.setDateNotification(rep.getDateReponseReclamation());
        notificationService.addNotification(not);
        rec.setNotifications(not);
        //assignNotificationToReclamation(idReclamation, not.getIdNotification());
        rec.setReponseReclamation(rep);
        rec.setEtatReclamation("Traitee");
        reclamationRepository.save(rec);

    }


    public void ArchiverReclamation(Long idReclamation) {
        Reclamation reclamation = reclamationRepository.findById(idReclamation)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
        reclamation.setArchived(true);
        reclamationRepository.save(reclamation);
    }



    public List<Reclamation> recupererReclamationsTrieesParDate() {
        return reclamationRepository.findAllByOrderByDateReclamation();
    }




    // @Override
    //public void assignNotificationToReclamation(Long idReclamation, Long idNotification) {
    //Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
    //Notification not =notificationRepository.findById(idNotification).orElse(null);
    //rec.setNotifications(not);
    // reclamationRepository.save(rec);
    // }

    //@Override
   // public void assignReclamationToUser(Long idReclamation, Long id) {
      //  Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
      //  User user = userRepository.findById(id).orElse(null);
     //   rec.setUser(user);
//  }


}
