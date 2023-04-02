package com.esprit.pidev.RestController;

import com.esprit.pidev.entities.Objectif;
import com.esprit.pidev.services.IObjectifService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/objectif")
public class ObjectifController {

    @Autowired

    private IObjectifService objectifService;

    @PostMapping("/")
    public ResponseEntity<Objectif> add (@RequestBody Objectif objectif){

        Objectif saved = objectifService.addObjectif(objectif);
        return new ResponseEntity<Objectif>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Objectif> update(@RequestBody Objectif objectif) {
        Objectif updated = objectifService.updateObjectif(objectif);
        return new ResponseEntity<Objectif>(updated, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Objectif> findById(@PathVariable(value = "id") Long id) {
        Optional<Objectif> objectif = objectifService.retrieveObjectifById(id);
        return new ResponseEntity<Objectif>(objectif.get(), HttpStatus.FOUND);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Collection<Objectif>> getAll() {
        Collection<Objectif> objectifs = objectifService.retrieveAllObjectif();
        return new ResponseEntity<Collection<Objectif>>(objectifs, HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = true) Long id) {
        objectifService.retrieveObjectifById(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

}
