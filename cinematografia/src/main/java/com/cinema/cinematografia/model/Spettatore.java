package com.cinema.cinematografia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="spettatore")
public class Spettatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome ;
    private String cognome ;

    @OneToOne
    @JoinColumn(name = "idBiglietto")
    private Film film;
    @Transient
    private int idBiglietto ;
    private int eta;


}
