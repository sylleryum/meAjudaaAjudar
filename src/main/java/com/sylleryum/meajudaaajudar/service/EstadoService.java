package com.sylleryum.meajudaaajudar.service;

import com.sylleryum.meajudaaajudar.entity.Estado;
import com.sylleryum.meajudaaajudar.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Page<Estado> findByNome(String nome, Pageable pageable) {
        return estadoRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Estado> findByUf(String nome, Pageable pageable) {
        return estadoRepository.findByUfContainingIgnoreCase(nome, pageable);
    }

    public Page<Estado> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    public <S extends Estado> S save(S s) {
        return estadoRepository.save(s);
    }

    public <S extends Estado> Iterable<S> saveAll(Iterable<S> iterable) {
        return estadoRepository.saveAll(iterable);
    }

    public Optional<Estado> findById(Long integer) {
        return estadoRepository.findById(integer);
    }

    public boolean existsById(Long aLong) {
        return estadoRepository.existsById(aLong);
    }

    public Iterable<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Iterable<Estado> findAllById(Iterable<Long> iterable) {
        return estadoRepository.findAllById(iterable);
    }

    public long count() {
        return estadoRepository.count();
    }

    public void deleteById(Long aLong) {
        estadoRepository.deleteById(aLong);
    }

    public void delete(Estado estado) {
        estadoRepository.delete(estado);
    }

    public void deleteAll(Iterable<? extends Estado> iterable) {
        estadoRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        estadoRepository.deleteAll();
    }
}
