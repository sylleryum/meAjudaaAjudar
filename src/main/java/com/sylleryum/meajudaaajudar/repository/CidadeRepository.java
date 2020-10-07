package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {


    Page<Cidade> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
