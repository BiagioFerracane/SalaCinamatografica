package com.cinema.cinematografia.service;

import com.cinema.cinematografia.exception.FilmVietatoAiMinoriException;
import com.cinema.cinematografia.exception.SalaAlComppletoException;
import com.cinema.cinematografia.exception.SalaNonTrovataException;
import com.cinema.cinematografia.model.SalaCinematografica;
import com.cinema.cinematografia.model.Spettatore;

import java.util.List;
import java.util.Optional;

public interface SalaCinematograficaService {
    Object save(SalaCinematografica sala);
    Boolean deleteById(int id);
    Optional<SalaCinematografica> getById(int id);
    List<SalaCinematografica> getAll();
    Boolean svuotaSala(int id) throws SalaNonTrovataException, FilmVietatoAiMinoriException, SalaAlComppletoException;
    void ingressoSala( int idSala, List<Spettatore> spettatori) throws SalaNonTrovataException;
    double calcolaIncasso(int id) throws SalaNonTrovataException;

    List<Spettatore> findAllSpettatoriBySala(Optional<SalaCinematografica> sala);
}
