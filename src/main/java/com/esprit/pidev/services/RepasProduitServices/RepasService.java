package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.RepasProduit.Repas;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
