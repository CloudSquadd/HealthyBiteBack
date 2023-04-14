package com.esprit.pidev.RestController.ReclamationEtReponseController;

import com.esprit.pidev.entities.ReclamationEtReponse.Reclamation;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReclamationRepository;
import com.esprit.pidev.services.ReclamationEtReponseService.IReclamation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReclamationController {
    IReclamation iReclamation;
    ReclamationRepository reclamationRepository;

    @PostMapping("/assignRepasToReclamation/{idReclamation}/{idRepas}")
    public void assignRepasToReclamation( @PathVariable("idReclamation") Long idReclamation , @PathVariable("idRepas")Long idRepas) {
        iReclamation.assignRepasToReclamation( idReclamation, idRepas);
    }
    @PostMapping("/assignResponseToReclamation/{idReclamation}/{idReponseReclamation}")
    public void assignResponseToReclamation(@PathVariable("idReclamation") Long idReclamation, @PathVariable("idReponseReclamation") Long idReponseReclamation) {
        iReclamation.assignResponseToReclamation(idReclamation, idReponseReclamation);
    }

    @PostMapping("/assignProduitToReclamation/{idReclamation}/{idProduit}")
    public void assignProduitToReclamation( @PathVariable("idReclamation") Long idReclamation , @PathVariable("idProduit")Long idProduit) {
        iReclamation.assignProduitToReclamation(idReclamation, idProduit);
    }

    @PutMapping("/updateReclamation")
    public Reclamation updateReclamation(@RequestBody Reclamation rec) {
        return iReclamation.updateReclamation(rec);
    }

    @GetMapping("/retrieveReclamationById/{idReclamation}")
    public Reclamation retrieveReclamationById(@PathVariable("idReclamation") Long idReclamation) {
        return iReclamation.retrieveReclamationById(idReclamation);
    }

    @GetMapping("/retrieveAllReclamation")
    public List<Reclamation> retrieveAllReclamation() {
        return iReclamation.retrieveAllReclamation();
    }

    @DeleteMapping("/ArchiverReclamation/{idReclamation}")
    public void ArchiverReclamation(@PathVariable("idReclamation") Long idReclamation) {
        iReclamation.ArchiverReclamation(idReclamation);
    }




    @GetMapping("/archived-reclamations")
    public List<Reclamation> getArchivedReclamations(Model model) {
        List<Reclamation> archivedReclamations = reclamationRepository.findByArchivedTrue();
        model.addAttribute("archivedReclamations", archivedReclamations);
        return archivedReclamations;
    }

    @GetMapping("/retrieveReclamation")
    public List<Reclamation> retrieveReclamation(boolean archived) {
        return iReclamation.retrieveReclamation(archived);
    }

    @GetMapping("/retrieveAllReclamationByUser/{id}")
    public List<Reclamation> retrieveAllReclamationByUser(@PathVariable("id") long userId) {
        return iReclamation.retrieveAllReclamationByUser(userId);
    }

    @GetMapping("/afficherReclamationsTries")
    public List<Reclamation> afficherReclamationsTries(Model model) {
        List<Reclamation> reclamations = reclamationRepository.findAllByOrderByDateReclamation();
        model.addAttribute("reclamations", reclamations);
        return reclamations;
    }
}



//@PostMapping("/assignNotificationToReclamation/{idReclamation}/{idNotification}")
//public void assignNotificationToReclamation(@PathVariable("idReclamation") Long idReclamation,@PathVariable("idNotification") Long idNotification){
//  iReclamation.assignNotificationToReclamation(idReclamation,idNotification);
//}

    //@PostMapping("/assignReclamationToUser/{idReclamation}/{id}")
    //public void assignReclamationToUser(@PathVariable("idReclamation") Long idReclamation,@PathVariable("id") Long id){
    // iReclamation.assignReclamationToUser(idReclamation,id);
    //}


