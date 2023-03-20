package com.cinema.cinematografia.service;

import com.cinema.cinematografia.model.Film;

import java.util.List;

public interface FilmService {
    Film save(Film film);
    Film findById(int id);
    List<Film> findAll();
    void delete(Film film);
}
