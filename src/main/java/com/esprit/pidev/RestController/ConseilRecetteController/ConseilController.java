package com.esprit.pidev.RestController.ConseilRecetteController;

import com.esprit.pidev.entities.ConseilRecette.Conseil;
import com.esprit.pidev.services.ConseilRecetteServices.IConseilService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/conseil")

public class ConseilController {

    @Autowired

    private IConseilService conseilService;
    @PostMapping("/")
    public ResponseEntity<Conseil> addConseil(@RequestBody Conseil conseil){

        Conseil saved = conseilService.addConseil(conseil);
        return new ResponseEntity<Conseil>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Conseil> updateConseil(@PathVariable("id")Long id ,@RequestBody Conseil conseil) {
        Conseil updated = conseilService.updateConseil(id, conseil);
        return new ResponseEntity<Conseil>(updated, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Conseil> findConseilById(@PathVariable(value = "id") Long id) {
        Optional<Conseil> conseil = conseilService.retrieveConseilById(id);
        return new ResponseEntity<Conseil>(conseil.get(), HttpStatus.FOUND);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Collection<Conseil>> getAllConseils() {
        Collection<Conseil> conseils = conseilService.retrieveAllConseil();
        return new ResponseEntity<Collection<Conseil>>(conseils, HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteConseil(@PathVariable(value = "id", required = true) Long id) {
        conseilService.deleteConseil(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
