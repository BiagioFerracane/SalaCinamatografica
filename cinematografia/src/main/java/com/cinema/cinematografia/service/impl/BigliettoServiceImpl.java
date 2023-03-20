package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.model.Biglietto;
import com.cinema.cinematografia.model.Film;
import com.cinema.cinematografia.repository.BigliettoRepository;
import com.cinema.cinematografia.service.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
    @Transactional
    public class BigliettoServiceImpl implements BigliettoService {

        @Autowired
        private BigliettoRepository bigliettoRepository;

        @Override
        public Biglietto save(Biglietto biglietto) {
            return bigliettoRepository.save(biglietto);
        }

        @Override
        public Biglietto findById(int id) {
            return bigliettoRepository.findById(id).orElse(null);
        }



    @Override
        public List<Biglietto> findByFilm(Film film) {
            return bigliettoRepository.findByFilm(film);
        }

        @Override
        public List<Biglietto> findAll() {
            return bigliettoRepository.findAll();
        }

        @Override
        public void delete(Biglietto biglietto) {
            bigliettoRepository.delete(biglietto);
        }
    }

