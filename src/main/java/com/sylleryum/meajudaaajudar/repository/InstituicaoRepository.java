package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Estado;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends PagingAndSortingRepository<Instituicao, Long> {

    Page<Instituicao> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
