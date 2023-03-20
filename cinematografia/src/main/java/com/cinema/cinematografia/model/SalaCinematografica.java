package com.cinema.cinematografia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="salaCinematografica")
public class SalaCinematografica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private int idFilm ;
    private int postiTotali;
    @OneToOne
    @JoinColumn(name = "idFilm")
    private Film film;
    @Transient
    private int idPrenotazione ;
    @ManyToOne
    @JoinColumn(name = "idPrenotazione")
    private Prenotazione prenotazione;
}
