package com.esprit.pidev.services;

import com.esprit.pidev.entities.Conseil;
import com.esprit.pidev.repository.ConseilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Conseil updateConseil(Conseil conseil) {
        return conseilRepository.save(conseil);
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
