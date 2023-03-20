package com.cinema.cinematografia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="biglietto")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int posizione;
    private double prezzo;
    @Transient
    private int idFilm;
    @ManyToOne
    @JoinColumn(name = "idFilm")
    private Film film;
}
