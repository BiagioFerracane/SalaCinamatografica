package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.model.Prenotazione;
import com.cinema.cinematografia.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/")
    public Prenotazione salvaPrenotazione(@RequestBody Prenotazione prenotazione) {
        return prenotazioneService.salvaPrenotazione(prenotazione);
    }

    @GetMapping("/")
    public List<Prenotazione> trovaTutteLePrenotazioni() {
        return prenotazioneService.trovaTutteLePrenotazioni();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> trovaPrenotazionePerId(@PathVariable("id") int id) {
        Optional<Prenotazione> prenotazione = prenotazioneService.trovaPrenotazionePerId(id);
        if (prenotazione.isPresent()) {
            return ResponseEntity.ok(prenotazione.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void eliminaPrenotazione(@PathVariable("id") int id) {
        prenotazioneService.eliminaPrenotazione(id);
    }

}
