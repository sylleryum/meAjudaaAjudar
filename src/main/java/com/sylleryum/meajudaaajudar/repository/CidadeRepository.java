package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {

    @Query(value = "select distinct A from Cidade as A inner join Instituicao as B on A.id = B.cidadeId")
    Page<Cidade> findAllActiveCidades(Pageable pageable);

    Page<Cidade> findByNomeContainingIgnoreCase(String nome, Pageable pageable);



}
