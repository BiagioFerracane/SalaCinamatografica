package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.exception.FilmVietatoAiMinoriException;
import com.cinema.cinematografia.exception.SalaAlComppletoException;
import com.cinema.cinematografia.exception.SalaNonTrovataException;
import com.cinema.cinematografia.model.SalaCinematografica;
import com.cinema.cinematografia.model.Spettatore;
import com.cinema.cinematografia.service.CinemaService;
import com.cinema.cinematografia.service.SalaCinematograficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salaCinematografica")
public class SalaCinematograficaController {

    @Autowired
    private SalaCinematograficaService salaService;
    private CinemaService cinemaService;

    @PostMapping("/add")
    public ResponseEntity<SalaCinematografica> addSala(@RequestBody SalaCinematografica sala) {
        return ResponseEntity.ok((SalaCinematografica) salaService.save(sala));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSala(@PathVariable int id) {
        Boolean result = salaService.deleteById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaCinematografica> getSalaById(@PathVariable int id) {
        Optional<SalaCinematografica> sala = salaService.getById(id);
        if (sala.isPresent()) {
            return ResponseEntity.ok(sala.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<SalaCinematografica>> getAllSale() {
        List<SalaCinematografica> sale = salaService.getAll();
        return ResponseEntity.ok(sale);
    }
    @PostMapping("/sala/svuota/{id}")
    public ResponseEntity<String> svuotaSala(@PathVariable int id) {
        try {
            Boolean result = salaService.svuotaSala(id);
            if (result) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SalaNonTrovataException e) {
            return ResponseEntity.notFound().build();
        } catch (FilmVietatoAiMinoriException e) {
            return ResponseEntity.badRequest().body("Il film è vietato ai minori");
        } catch (SalaAlComppletoException e) {
            return ResponseEntity.badRequest().body("La sala è al completo");
        }
    }

    @PostMapping("/sala/ingresso/{idSala}")
    public ResponseEntity<String> ingressoSala(@PathVariable int idSala, @RequestBody List<Spettatore> spettatori) {
        try {
            salaService.ingressoSala(idSala, spettatori);
            return ResponseEntity.ok().build();
        } catch (SalaNonTrovataException e) {
            return ResponseEntity.notFound().build();
        } catch (FilmVietatoAiMinoriException e) {
            return ResponseEntity.badRequest().body("Il film è vietato ai minori");
        } catch (SalaAlComppletoException e) {
            return ResponseEntity.badRequest().body("La sala è al completo");
        }
    }

    @GetMapping("/sala/incasso/{id}")
    public ResponseEntity<Double> calcolaIncasso(@PathVariable int id) {
        try {
            double incasso = salaService.calcolaIncasso(id);
            return ResponseEntity.ok().body(incasso);
        } catch (SalaNonTrovataException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<Spettatore>> findAllSpettatoriBySala(@PathVariable int id) {
        Optional<SalaCinematografica> sala = salaService.getById(id);
        if (sala.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Spettatore> spettatori = salaService.findAllSpettatoriBySala(sala);
        return ResponseEntity.ok(spettatori);
    }
}

