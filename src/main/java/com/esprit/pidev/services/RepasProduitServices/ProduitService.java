package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.repository.RepasproduitRepository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProduitService implements IProduit{

    ProduitRepository produitRepository;

    @Override
    public Produit addProduit(Produit pr) {
        return produitRepository.save(pr);
    }

    @Override
    public Produit updateProduit(Produit pr) {
        return produitRepository.save(pr);
    }

    @Override
    public Produit retrieveProduitById(Long id) {

        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> retrieveAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Set<Produit> getProduitByUserId(Long id) {
        return produitRepository.findByUserId(id);
    }


}
