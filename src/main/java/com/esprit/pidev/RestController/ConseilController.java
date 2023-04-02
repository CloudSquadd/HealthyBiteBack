package com.esprit.pidev.RestController;

import com.esprit.pidev.entities.Conseil;
import com.esprit.pidev.services.IConseilService;
import com.esprit.pidev.services.IRecetteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/conseil")
public class ConseilController {

    @Autowired

    private IConseilService conseilService;
    @PostMapping("/")
    public ResponseEntity<Conseil> add (@RequestBody Conseil conseil){

        Conseil saved = conseilService.addConseil(conseil);
        return new ResponseEntity<Conseil>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Conseil> update(@RequestBody Conseil conseil) {
        Conseil updated = conseilService.updateConseil(conseil);
        return new ResponseEntity<Conseil>(updated, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Conseil> findById(@PathVariable(value = "id") Long id) {
        Optional<Conseil> conseil = conseilService.retrieveConseilById(id);
        return new ResponseEntity<Conseil>(conseil.get(), HttpStatus.FOUND);
    }


    @GetMapping(value = "/")
    public ResponseEntity<Collection<Conseil>> getAll() {
        Collection<Conseil> conseils = conseilService.retrieveAllConseil();
        return new ResponseEntity<Collection<Conseil>>(conseils, HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = true) Long id) {
        conseilService.retrieveConseilById(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
