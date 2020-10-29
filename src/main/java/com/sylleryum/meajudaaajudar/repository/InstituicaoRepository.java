package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Instituicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends PagingAndSortingRepository<Instituicao, Long>, JpaSpecificationExecutor<Instituicao> {

    //*only cidade passed
    Page<Instituicao> findByCidadeId(long cidadeId, Pageable pageable);
    //*cidade and causas
    @Query("select i from Instituicao i where i.cidadeId = ?1 and i.causaId in (?2)")
    Page<Instituicao> findByCidadeIdAndCausaIds(long cidadeId, Long[] causaIds, Pageable pageable);

    //*only nome
    Page<Instituicao> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    //*nome and causas
    @Query("select i from Instituicao i where lower(i.nome) like lower(concat('%',?1,'%')) and i.causaId in (?2)")
    Page<Instituicao> findByNomeAndCausaIds(String nomeInstituicao, Long[] causaIds, Pageable pageable);

    //*cidade and nome
    @Query("select i from Instituicao i where i.cidadeId = ?1 and lower(i.nome) like lower(concat('%',?2,'%'))")
    Page<Instituicao> findByCidadeIdAndNome(long cidadeId, String nomeInstituicao, Pageable pageable);
    //*cidade, nome and causas
    @Query("select i from Instituicao i where i.cidadeId = ?1 and lower(i.nome) like lower(concat('%',?2,'%')) and i.causaId in (?3)")
    Page<Instituicao> findByCidadeIdAndNomeAndCausaIds(long cidadeId, String nomeInstituicao, Long[] causaIds, Pageable pageable);

}
