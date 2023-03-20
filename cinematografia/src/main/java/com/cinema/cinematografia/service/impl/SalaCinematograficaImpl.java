package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.exception.FilmVietatoAiMinoriException;
import com.cinema.cinematografia.exception.SalaAlComppletoException;
import com.cinema.cinematografia.exception.SalaNonTrovataException;
import com.cinema.cinematografia.model.*;
import com.cinema.cinematografia.repository.BigliettoRepository;
import com.cinema.cinematografia.repository.PrenotazioneRepository;
import com.cinema.cinematografia.repository.SalaCinematograficaRepository;
import com.cinema.cinematografia.repository.SpettatoreRepository;
import com.cinema.cinematografia.service.SalaCinematograficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalaCinematograficaImpl implements SalaCinematograficaService {
    @Autowired
    private SalaCinematograficaRepository salaCinematograficaRepository;
    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private SpettatoreRepository spettatoreRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public SalaCinematografica save(SalaCinematografica sala) {
        return (SalaCinematografica) salaCinematograficaRepository.save(sala);
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.TRUE;
        Optional<SalaCinematografica> libroDaCancellare = salaCinematograficaRepository.findById(id);
        if (libroDaCancellare.isPresent()) {
            try {
                salaCinematograficaRepository.deleteById(id);

                res = Boolean.TRUE;

            } catch (Exception e) {
                res = Boolean.FALSE;

            }
        }
        return res;
    }

    @Override
    public Optional<SalaCinematografica> getById(int id) {
        return salaCinematograficaRepository.findById(id);
    }

    @Override
    public List<SalaCinematografica> getAll() {
        return salaCinematograficaRepository.findAll();

    }

    @Override
    public Boolean svuotaSala(int id) throws SalaNonTrovataException, FilmVietatoAiMinoriException, SalaAlComppletoException {

        Boolean res = Boolean.TRUE;
        SalaCinematografica sala = salaCinematograficaRepository.findById(id).orElseThrow(() -> new SalaNonTrovataException());
        List<Spettatore> spettatori = salaCinematograficaRepository.findAllSpettatoriBySala(sala);
        spettatori.clear();
        sala.setPostiTotali(sala.getPostiTotali());
        salaCinematograficaRepository.save(sala);


        return res;
    }


    @Override
    public void ingressoSala(int idSala, List<Spettatore> spettatori) throws SalaNonTrovataException {
        SalaCinematografica sala = salaCinematograficaRepository.findById(idSala).orElseThrow(() -> new SalaNonTrovataException());
        Film film = sala.getFilm();
        for (Spettatore spett : spettatori) {
            if (film != null && sala.getPrenotazione().getSpettatore().getEta() < film.getEtaMin()) {
                throw new FilmVietatoAiMinoriException();
            }
            if (sala.getPostiTotali() == 0) {
                throw new SalaAlComppletoException();
            }
            if (spettatori.size() >= sala.getPostiTotali()) {
                throw new SalaAlComppletoException();
            }
            sala.getPrenotazione().setSpettatore(spett);
        }
        save(sala);
    }

    @Override
    public double calcolaIncasso(int id) throws SalaNonTrovataException {
        double incasso = 0;
        boolean salaTrovata = false;
        List<SalaCinematografica> listaSale = salaCinematograficaRepository.findAll();
        for (SalaCinematografica sala : listaSale) {
            List<Spettatore> spettatori = salaCinematograficaRepository.findAllSpettatoriBySala(sala);

            for (Spettatore spett : spettatori) {

                Optional<Biglietto> biglietto = bigliettoRepository.findById(spett.getIdBiglietto());
                if (biglietto.isPresent()) {
                    incasso += incasso + biglietto.get().getPrezzo();
                }
                throw new SalaNonTrovataException();
            }


        }
        return incasso;
    }

@Override
    public List<Spettatore> findAllSpettatoriBySala(Optional<SalaCinematografica> sala) {
        List<Prenotazione> prenotazione = (List<Prenotazione>) sala.get().getPrenotazione();
    List<Spettatore> spettatori = new ArrayList<>();
        for(Prenotazione preno : prenotazione) {
            Spettatore spettatore = preno.getSpettatore();
             spettatori.add(spettatore);
        }
        return spettatori;
    }
}
