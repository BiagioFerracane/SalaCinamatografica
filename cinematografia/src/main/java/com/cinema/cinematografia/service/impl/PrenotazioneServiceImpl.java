package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.model.Prenotazione;
import com.cinema.cinematografia.repository.PrenotazioneRepository;
import com.cinema.cinematografia.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PrenotazioneServiceImpl implements PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
@Override

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
    Prenotazione save;
    save = prenotazioneRepository.save(prenotazione);
    return save;
    }
@Override
    public List<Prenotazione> trovaTutteLePrenotazioni() {
        return prenotazioneRepository.findAll();
    }
@Override
    public Optional<Prenotazione> trovaPrenotazionePerId(int id) {
        return prenotazioneRepository.findById(id);
    }
@Override
    public void eliminaPrenotazione(int id) {
        prenotazioneRepository.deleteById(id);
    }
}
