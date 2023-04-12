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

    @PostMapping("/addReclamation")
    public Reclamation addReclamation(@RequestBody Reclamation rec){
        return iReclamation.addReclamation(rec);
    }
    @PutMapping("/updateReclamation")
    public Reclamation updateReclamation(@RequestBody Reclamation rec){
        return iReclamation.updateReclamation(rec);
    }
    @GetMapping("/retrieveReclamationById/{idReclamation}")
    public Reclamation retrieveReclamationById(@PathVariable("idReclamation") Long idReclamation){
        return iReclamation.retrieveReclamationById(idReclamation);
    }
    @GetMapping("/retrieveAllReclamation")
    public List<Reclamation> retrieveAllReclamation(){
        return iReclamation.retrieveAllReclamation();
    }
    @DeleteMapping("/deleteReclamation/{idReclamation}")
    public void deleteReclamation(@PathVariable("idReclamation") Long idReclamation){
        iReclamation.deleteReclamation(idReclamation);
    }
    public class Stats {
        private int traité;
        private int nonTraité;

        public Stats() {
            this.traité = 0;
            this.nonTraité = 0;
        }
        public Stats(int traité, int nonTraité) {
            this.traité = traité;
            this.nonTraité = nonTraité;
        }
    }
    @GetMapping("/pie-chart")
    public String getPieChart(Model model){
        List<Reclamation> réclamations = iReclamation.retrieveAllReclamation();
        int traitéCount = 0;
        int nonTraitéCount = 0;
        for (Reclamation réclamation : réclamations) {
            if (réclamation.getEtatReclamation().equals("traite")) {
                traitéCount++;
            } else {
                nonTraitéCount++;
            }
        }
        Stats stats = new Stats (traitéCount, nonTraitéCount);
        model.addAttribute("stats", stats);
        return "pie-chart";
    }
    @PostMapping("/assignResponseToReclamation/{idReclamation}/{idReponseReclamation}")
    public void assignResponseToReclamation(@PathVariable("idReclamation") Long idReclamation,@PathVariable("idReponseReclamation") Long idReponseReclamation){
        iReclamation.assignResponseToReclamation(idReclamation,idReponseReclamation);
    }
    @PostMapping("/assignNotificationToReclamation/{idReclamation}/{idNotification}")
    public void assignNotificationToReclamation(@PathVariable("idReclamation") Long idReclamation,@PathVariable("idNotification") Long idNotification){
        iReclamation.assignNotificationToReclamation(idReclamation,idNotification);
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
}
