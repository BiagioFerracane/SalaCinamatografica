package com.cinema.cinematografia.repository;

import com.cinema.cinematografia.model.Biglietto;
import com.cinema.cinematografia.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Integer>{
    List<Biglietto> findByFilm(Film film);
}
