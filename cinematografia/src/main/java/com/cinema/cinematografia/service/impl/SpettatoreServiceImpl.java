package com.cinema.cinematografia.service.impl;

import com.cinema.cinematografia.model.Biglietto;
import com.cinema.cinematografia.model.Spettatore;
import com.cinema.cinematografia.repository.BigliettoRepository;
import com.cinema.cinematografia.repository.SpettatoreRepository;
import com.cinema.cinematografia.service.SpettatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SpettatoreServiceImpl implements SpettatoreService {
    @Autowired
    private SpettatoreRepository  spettatoreRepository;
    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Override
    public Spettatore save(Spettatore spettatore) {
        return (Spettatore) spettatoreRepository.save(spettatore);
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.TRUE;
        Optional<Spettatore> spettatoreDaCancellare = spettatoreRepository.findById(id);
        if (spettatoreDaCancellare.isPresent()) {
            try {
                spettatoreRepository.deleteById(id);

                res = Boolean.TRUE;

            } catch (Exception e) {
                res = Boolean.FALSE;

            }
        }
        return res;
    }

    @Override
    public Optional<Spettatore> getById(int id) {
        return  spettatoreRepository.findById(id);
    }

    @Override
    public List<Spettatore> getAll() {
        return spettatoreRepository.findAll();
    }

    @Override
    public Boolean isMaggiorenne(int id) {
        return null;
    }

    @Override
    public double getSconto(int idspettatore) {
        double sconto = 0;
        Optional<Spettatore> spettatore= spettatoreRepository.findById(idspettatore);
      int idBiglietto = spettatore.get().getIdBiglietto();
      Optional<Biglietto> biglietto = bigliettoRepository.findById(idBiglietto);
        if(spettatore.isPresent()){
           int eta = spettatore.get().getEta();
           if(eta<5){
               sconto = biglietto.get().getPrezzo() * 0.5;
           } if(eta >70) {
                sconto = biglietto.get().getPrezzo() * 0.1;

            }
        }
        return sconto;
    }
}
