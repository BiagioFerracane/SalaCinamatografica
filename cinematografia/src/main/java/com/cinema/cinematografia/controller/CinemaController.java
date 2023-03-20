package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.model.Cinema;
import com.cinema.cinematografia.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public List<Cinema> getAllCinema() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public Cinema getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCinema(@RequestBody Cinema cinema) {
        cinemaService.saveCinema(cinema);
    }

    @PutMapping("/{id}")
    public void updateCinema(@PathVariable int id, @RequestBody Cinema cinema) {
        Cinema existingCinema = cinemaService.getCinemaById(id);
        existingCinema.setNome(cinema.getNome());
        existingCinema.setSalaCinematografica(cinema.getSalaCinematografica());
        cinemaService.saveCinema(existingCinema);
    }

    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinema(id);
    }
}