package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Fournisseur;
import com.esprit.pidev.repository.RepasproduitRepository.FournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FournisseurService implements IFournisseur{

    FournisseurRepository fournisseurRepository;
    @Override
    public Fournisseur addFournisseur(Fournisseur fr) {
        return fournisseurRepository.save(fr);
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fr) {
        return fournisseurRepository.save(fr);
    }

    @Override
    public Fournisseur retrieveFournisseurById(Long id) {
        return fournisseurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Fournisseur> retrieveAllFournisseur() {
        return fournisseurRepository.findAll();
    }

    @Override
    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
