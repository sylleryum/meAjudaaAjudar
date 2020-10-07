package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.entity.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//@RestResource(rel = "estados")
@Repository
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {

    Page<Estado> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Estado> findByUfContainingIgnoreCase(String nome, Pageable pageable);
}
