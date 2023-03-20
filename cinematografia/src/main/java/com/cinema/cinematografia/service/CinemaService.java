package com.cinema.cinematografia.service;

import com.cinema.cinematografia.model.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> getAllCinemas();
    Cinema getCinemaById(int id);
    void saveCinema(Cinema cinema);
    void deleteCinema(int id);
}
