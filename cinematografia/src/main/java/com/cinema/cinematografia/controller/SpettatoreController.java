package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.model.Spettatore;
import com.cinema.cinematografia.service.SpettatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spettatori")
public class SpettatoreController {

    @Autowired
    private SpettatoreService spettatoreService;

    @PostMapping
    public Spettatore save(@RequestBody Spettatore spettatore) {
        return spettatoreService.save(spettatore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id) {
        Boolean res = spettatoreService.deleteById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Spettatore>> getById(@PathVariable("id") int id) {
        Optional<Spettatore> spettatore = spettatoreService.getById(id);
        return ResponseEntity.ok(spettatore);
    }

    @GetMapping
    public ResponseEntity<List<Spettatore>> getAll() {
        List<Spettatore> spettatori = spettatoreService.getAll();
        return ResponseEntity.ok(spettatori);
    }

    @GetMapping("/{id}/isMaggiorenne")
    public ResponseEntity<Boolean> isMaggiorenne(@PathVariable("id") int id) {
        Boolean isMaggiorenne = spettatoreService.isMaggiorenne(id);
        return ResponseEntity.ok(isMaggiorenne);
    }

    @GetMapping("/{id}/sconto")
    public ResponseEntity<Double> getSconto(@PathVariable("id") int id) {
        double sconto = spettatoreService.getSconto(id);
        return ResponseEntity.ok(sconto);
    }
}

