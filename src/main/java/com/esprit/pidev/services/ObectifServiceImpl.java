package com.esprit.pidev.services;

import com.esprit.pidev.entities.Objectif;
import com.esprit.pidev.repository.ObjectifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ObectifServiceImpl implements IObjectifService{

    @Autowired
    private ObjectifRepository objectifRepository;

    @Override
    public Objectif addObjectif(Objectif objectif) {

        return objectifRepository.save(objectif);
    }

    @Override
    public Objectif updateObjectif(Objectif objectif) {
        return objectifRepository.save(objectif);
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
}
