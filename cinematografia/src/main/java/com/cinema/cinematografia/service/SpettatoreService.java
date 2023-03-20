package com.cinema.cinematografia.service;

import com.cinema.cinematografia.model.Spettatore;

import java.util.List;
import java.util.Optional;

public interface SpettatoreService {
    Spettatore save(Spettatore spettatore);
    Boolean deleteById(int id);
    Optional<Spettatore>getById(int id);
    List<Spettatore> getAll();
public Boolean isMaggiorenne( int id);
public double getSconto(int idspettatore);




}
