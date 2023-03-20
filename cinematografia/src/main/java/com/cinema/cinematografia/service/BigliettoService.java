package com.cinema.cinematografia.service;

import com.cinema.cinematografia.model.Biglietto;
import com.cinema.cinematografia.model.Film;

import java.util.List;

public interface BigliettoService {
    Biglietto save(Biglietto biglietto);
    Biglietto findById(int id);


    List<Biglietto> findByFilm(Film film);

    List<Biglietto> findAll();
    void delete(Biglietto biglietto);
}
