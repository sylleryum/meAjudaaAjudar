package com.sylleryum.meajudaaajudar.service;

import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
//should be implemented on an interface ¯\_(ツ)_/¯

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Page<Cidade> findByNome(String nome, Pageable pageable) {
        return cidadeRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Iterable<Cidade> findAll(Sort sort) {
        return cidadeRepository.findAll(sort);
    }

    public Page<Cidade> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }

    public <S extends Cidade> S save(S s) {
        return cidadeRepository.save(s);
    }

    public <S extends Cidade> Iterable<S> saveAll(Iterable<S> iterable) {
        return cidadeRepository.saveAll(iterable);
    }

    public Optional<Cidade> findById(Long aLong) {
        return cidadeRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return cidadeRepository.existsById(aLong);
    }

    public Iterable<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Iterable<Cidade> findAllById(Iterable<Long> iterable) {
        return cidadeRepository.findAllById(iterable);
    }

    public long count() {
        return cidadeRepository.count();
    }

    public void deleteById(Long aLong) {
        cidadeRepository.deleteById(aLong);
    }

    public void delete(Cidade cidade) {
        cidadeRepository.delete(cidade);
    }

    public void deleteAll(Iterable<? extends Cidade> iterable) {
        cidadeRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        cidadeRepository.deleteAll();
    }
}
