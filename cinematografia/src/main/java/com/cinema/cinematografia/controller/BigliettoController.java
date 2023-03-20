package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.model.Biglietto;
import com.cinema.cinematografia.model.Film;
import com.cinema.cinematografia.service.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biglietto")
public class BigliettoController {

    @Autowired
    private BigliettoService bigliettoService;

    @PostMapping
    public ResponseEntity<Biglietto> save(@RequestBody Biglietto biglietto) {
        Biglietto savedBiglietto = bigliettoService.save(biglietto);
        return new ResponseEntity<>(savedBiglietto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biglietto> findById(@PathVariable("id") int id) {
        Biglietto biglietto = bigliettoService.findById(id);
        if (biglietto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(biglietto, HttpStatus.OK);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<List<Biglietto>> findByFilm(@PathVariable("id") int idFilm) {
        Film film = new Film();
        film.setId(idFilm);
        List<Biglietto> biglietti = bigliettoService.findByFilm(film);
        return new ResponseEntity<>(biglietti, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Biglietto>> findAll() {
        List<Biglietto> biglietti = bigliettoService.findAll();
        return new ResponseEntity<>(biglietti, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        Biglietto biglietto = bigliettoService.findById(id);
        if (biglietto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bigliettoService.delete(biglietto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
