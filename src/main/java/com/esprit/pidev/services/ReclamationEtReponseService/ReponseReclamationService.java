package com.esprit.pidev.services.ReclamationEtReponseService;

import com.esprit.pidev.entities.ReclamationEtReponse.ReponseReclamation;
import com.esprit.pidev.repository.ReclamationEtReponseRepository.ReponseReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReponseReclamationService implements IReponseReclamation {
    ReponseReclamationRepository reponseReclamationRepository;
    @Override
    public ReponseReclamation addReponseReclamation(ReponseReclamation repr) {
        return reponseReclamationRepository.save(repr);
    }

    @Override
    public ReponseReclamation updateReponseReclamation(ReponseReclamation repr) {
        return reponseReclamationRepository.save(repr);
    }

    @Override
    public ReponseReclamation retrieveReponseReclamationById(Long idReponseReclamation) {
        return reponseReclamationRepository.findById(idReponseReclamation).orElse(null);
    }

    @Override
    public List<ReponseReclamation> retrieveAllReponseReclamation() {
        return reponseReclamationRepository.findAll();
    }

    @Override
    public void deleteReponseReclamation(Long idReponseReclamation) {
        reponseReclamationRepository.deleteById(idReponseReclamation);
    }

    // @Override
    //public ReponseReclamation assignResponseReclamationToReclamation(Long idReponseReclamation, Long idReclamation) {
    //  ReponseReclamation repr = reponseReclamationRepository.findById(idReponseReclamation).orElse(null);
    //Reclamation rec = reclamationRepository.findById(idReclamation).orElse(null);
    //repr.getReclamation().getDateReclamation();
    //repr.getReclamation().getTextReclamation();
    //repr.getReclamation().setEtatReclamation("Traitéé");
    //return reponseReclamationRepository.save(repr);
    //}
}
