package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.model.Film;
import com.cinema.cinematografia.repository.FilmRepository;
import com.cinema.cinematografia.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    @Transactional
    public class FilmServiceImpl implements FilmService {

        @Autowired
        private FilmRepository filmRepository;

        @Override
        public Film save(Film film) {
            return filmRepository.save(film);
        }

        @Override
        public Film findById(int id) {
            return filmRepository.findById(id).orElse(null);
        }

        @Override
        public List<Film> findAll() {
            return filmRepository.findAll();
        }

        @Override
        public void delete(Film film) {
            filmRepository.delete(film);
        }
    }

