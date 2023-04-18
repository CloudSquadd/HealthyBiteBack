package com.esprit.pidev.services;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Objectif;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.exceptions.RecetteNotFoundException;
import com.esprit.pidev.repository.ObjectifRepository;
import com.esprit.pidev.services.UserRoleService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ObectifServiceImpl implements IObjectifService {

    @Autowired
    private ObjectifRepository objectifRepository;

    @Autowired
    private UserService userService;

    @Override
    public Objectif addObjectif(Objectif objectif) {

        return objectifRepository.save(objectif);
    }

    @Override
    public Objectif addObjectif(Objectif objectif, Long userId) {
        User user = userService.retrieveUserById(userId);
        if (user == null) {
            throw new RecetteNotFoundException("Recette not found");
        }
        objectif.setUser(user);
        return objectifRepository.save(objectif);
    }

    @Override
    public Objectif updateObjectif(Long id, Objectif objectif) {
        Objectif existigObjectif = objectifRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Objectif with id " + id + " not found"));

        if (objectif.getObjectifPoid() != 0.0f) {
            existigObjectif.setObjectifPoid(objectif.getObjectifPoid());
        }
        if (objectif.getTaille() != 0.0f) {
            existigObjectif.setTaille(objectif.getTaille());
        }
        if (objectif.getTypeActivite() != null) {
            existigObjectif.setTypeActivite(objectif.getTypeActivite());
        }
        if (objectif.getPoidDepart() != 0.0f) {
            existigObjectif.setPoidDepart(objectif.getPoidDepart());
        }
        if (objectif.getPoidActuel() != 0.0f) {
            existigObjectif.setPoidActuel(objectif.getPoidActuel());
        }
        return objectifRepository.save(existigObjectif);
    }

    @Override
    public Optional<Objectif> retrieveObjectifById(Long id) {
        return objectifRepository.findById(id);
    }

    @Override
    public List<Objectif> retrieveAllObjectif() {
        return objectifRepository.findAll();
    }

    @Override
    public void deleteObjectif(Long id) {
        this.objectifRepository.deleteById(id);

    }

    @Override
    public List<Objectif> findSimilarObjectifs(Long poidDepart, Long objectifPoid ) {


        return objectifRepository.findByPoidDeDepardWithTolerance(poidDepart-5, poidDepart + 5, objectifPoid-5, objectifPoid+5  );
    }
}
