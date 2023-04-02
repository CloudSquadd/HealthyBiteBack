package com.esprit.pidev.services;

import com.esprit.pidev.entities.Conseil;

import java.util.List;
import java.util.Optional;

public interface IConseilService {

    Conseil addConseil(Conseil conseil);
    Conseil updateConseil(Conseil conseil);
    Optional<Conseil> retrieveConseilById(Long id);
    List<Conseil> retrieveAllConseil();
    void deleteConseil(Long id);
}
