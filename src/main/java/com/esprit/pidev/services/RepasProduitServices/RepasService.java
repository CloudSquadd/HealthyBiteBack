package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Repas;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RepasService implements IRepas{

    RepasRepository repasRepository;

    @Override
    public Repas addRepas(Repas rep) {
        return repasRepository.save(rep);
    }

    @Override
    public Repas updateRepas(Repas rep) {
        return repasRepository.save(rep);
    }

    @Override
    public Repas retrieveRepasById(Long id) {
        return repasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Repas> retrieveAllRepas() {
        return repasRepository.findAll();
    }

    @Override
    public void deleteRepas(Long id) {
            repasRepository.deleteById(id);
    }

    @Override
    public Set<Repas> getRepasByUserId(Long id) {
        return repasRepository.findByUserId(id);
    }

    @Override
    public int calculerCaloriesTotales(List<Repas> repasChoisis) {
        int caloriesTotales = 0;
        for (Repas repas : repasChoisis) {
            caloriesTotales += repas.getNutrition().getCalories();
        }
        return caloriesTotales;
    }
}
