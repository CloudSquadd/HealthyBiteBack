package com.esprit.pidev.services.CommandeLivraisonServices;

import com.esprit.pidev.entities.CommandeLivraison.LigneRepas;
import com.esprit.pidev.repository.CommandeLivraisonRepo.LigneRepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneRepasService implements ILigneRepasService {

    @Autowired
    private LigneRepasRepository ligneRepasRepository;


    public LigneRepas saveLigneRepas(LigneRepas ligneRepas) {
        return ligneRepasRepository.save(ligneRepas);
    }
    @Override
    public List<LigneRepas> getAllLigneRepas() {
        return ligneRepasRepository.findAll();
    }

    @Override
    public LigneRepas save(LigneRepas ligneRepas) {
        return ligneRepasRepository.save(ligneRepas);
    }


    @Override
    public LigneRepas updateLigneRepas(LigneRepas ligneRepas) {
        return ligneRepasRepository.save(ligneRepas);
    }

    @Override
    public void deleteLigneRepasById(Long id) {
        ligneRepasRepository.deleteById(id);
    }

    @Override
    public LigneRepas getLigneRepasById(Long id) {
        Optional<LigneRepas> ligneRepasOptional = ligneRepasRepository.findById(id);
        return ligneRepasOptional.orElse(null);
    }

    @Override
    public List<LigneRepas> getAllLignesRepas() {
        return ligneRepasRepository.findAll();
    }
    @Override
    public LigneRepas addLigneRepas(LigneRepas ligneRepas) {
        return ligneRepasRepository.save(ligneRepas);
    }

}
