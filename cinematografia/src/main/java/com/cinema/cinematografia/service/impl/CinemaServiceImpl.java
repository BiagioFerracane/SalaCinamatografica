package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.model.Cinema;
import com.cinema.cinematografia.repository.CinemaRepository;
import com.cinema.cinematografia.service.CinemaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
    @Transactional
    public class CinemaServiceImpl implements CinemaService {

        private final CinemaRepository cinemaRepository;

        public CinemaServiceImpl(CinemaRepository cinemaRepository) {
            this.cinemaRepository = cinemaRepository;
        }

        @Override
        public List<Cinema> getAllCinemas() {
            return cinemaRepository.findAll();
        }

        @Override
        public Cinema getCinemaById(int id) {
            return cinemaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cinema not found with id " + id));
        }

        @Override
        public void saveCinema(Cinema cinema) {
            cinemaRepository.save(cinema);
        }

        @Override
        public void deleteCinema(int id) {
            cinemaRepository.deleteById(id);
        }
    }

