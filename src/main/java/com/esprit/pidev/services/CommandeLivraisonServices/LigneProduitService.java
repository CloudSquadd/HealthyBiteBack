package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.LigneProduit;
import com.esprit.pidev.repository.CommandeLivraisonRepo.LigneProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneProduitService implements ILigneProduitService {

    @Autowired
    private LigneProduitRepository ligneProduitRepository;


    public LigneProduit saveLigneProduit(LigneProduit ligneProduit) {
        return ligneProduitRepository.save(ligneProduit);
    }

    @Override
    public LigneProduit updateLigneProduit(LigneProduit ligneProduit) {
        return ligneProduitRepository.save(ligneProduit);
    }

    @Override
    public LigneProduit addLigneProduit(LigneProduit ligneProduit) {
        return ligneProduitRepository.save(ligneProduit);
    }

    @Override
    public void deleteLigneProduitById(Long id) {
        ligneProduitRepository.deleteById(id);
    }
    @Override
    public LigneProduit getLigneProduitById(Long id) {
        Optional<LigneProduit> ligneProduitOptional = ligneProduitRepository.findById(id);
        return ligneProduitOptional.orElse(null);
    }
    @Override
    public List<LigneProduit> getAllLignesProduits() {
        return ligneProduitRepository.findAll();
    }

}
