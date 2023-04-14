package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.repository.RepasproduitRepository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProduitService implements IProduit{

    ProduitRepository produitRepository;

    //public void checkReclamationsByProduit(Long id) {
    // List<Object[]> result = produitRepository.countReclamationsByProduit();
    //// for (Object[] row : result) {
        //     String nomProduit = (String) row[0];
    // Long nombreReclamations = (Long) row[1];
    // if (nombreReclamations > 10) {
    //  Produit produit = produitRepository.findById(id).orElse(null);
    //  produit.setBloquee(true);
    //  produitRepository.save(produit);
    // }
    //}
    // }


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

    @Override
    @Transactional
    public void updateProduitBloqueStatus(Long idProduit) {
        produitRepository.updateProduitBloqueStatus(idProduit);
    }


}
