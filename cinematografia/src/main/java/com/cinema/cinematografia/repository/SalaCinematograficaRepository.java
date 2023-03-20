package com.cinema.cinematografia.repository;

import com.cinema.cinematografia.model.SalaCinematografica;
import com.cinema.cinematografia.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaCinematograficaRepository extends JpaRepository <SalaCinematografica, Integer> {
    public List<Spettatore> findAllSpettatoriBySala(SalaCinematografica sala);
    }

