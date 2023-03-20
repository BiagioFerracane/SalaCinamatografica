package com.cinema.cinematografia.service;

import com.cinema.cinematografia.model.Prenotazione;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneService {

    Prenotazione salvaPrenotazione(Prenotazione prenotazione);

    List<Prenotazione> trovaTutteLePrenotazioni();

    Optional<Prenotazione> trovaPrenotazionePerId(int id);

    void eliminaPrenotazione(int id);
}
