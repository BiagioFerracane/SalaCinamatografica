package com.cinema.cinematografia.repository;

import com.cinema.cinematografia.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpettatoreRepository extends JpaRepository<Spettatore, Integer> {

}
