package com.esprit.pidev.services;

import com.esprit.pidev.entities.Conseil;
import com.esprit.pidev.entities.Recette;
import com.esprit.pidev.repository.ConseilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConseilServiceImpl implements IConseilService {

    @Autowired
    private ConseilRepository conseilRepository;

    @Override
    public Conseil addConseil(Conseil conseil) {
        return conseilRepository.save(conseil);
    }

    @Override
    public Conseil updateConseil(Long id, Conseil conseil) {
        Conseil existingConseil = conseilRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Conseil with id " + id + " not found"));
        if(conseil.getDescription()==null){
            throw new IllegalArgumentException("No description is provided");
        }
        existingConseil.setDescription(conseil.getDescription());

        return conseilRepository.save(existingConseil);
    }

    @Override
    public Optional<Conseil> retrieveConseilById(Long id) {
        return conseilRepository.findById(id);
    }

    @Override
    public List<Conseil> retrieveAllConseil() {
        return conseilRepository.findAll();
    }

    @Override
    public void deleteConseil(Long id) {
        conseilRepository.deleteById(id);
    }
}
