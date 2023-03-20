package com.cinema.cinematografia.controller;

import com.cinema.cinematografia.model.Film;
import com.cinema.cinematografia.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/film")
    public class FilmController {

        @Autowired
        private FilmService filmService;

        @PostMapping
        public ResponseEntity<Film> save(@RequestBody Film film) {
            Film savedFilm = filmService.save(film);
            return new ResponseEntity<>(savedFilm, HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Film> findById(@PathVariable("id") int id) {
            Film film = filmService.findById(id);
            if (film == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(film, HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<Film>> findAll() {
            List<Film> films = filmService.findAll();
            return new ResponseEntity<>(films, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable("id") int id) {
            Film film = filmService.findById(id);
            if (film == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            filmService.delete(film);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

