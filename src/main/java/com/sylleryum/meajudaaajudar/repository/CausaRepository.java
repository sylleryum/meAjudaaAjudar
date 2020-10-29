package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CausaRepository extends CrudRepository<Causa, Long>{

    @Query(value = "select distinct A from Causa as A inner join Instituicao as B on A.id = B.causaId")
    Page<Causa> findAllActiveCausas(Pageable pageable);

    //select A, count(A.id) from causa as A inner join Instituicao as B on A.id = B.causa_id where cidade_id = 4174 group by A.id order by count(A.id) desc
    @Query(value = "select A, count(A.id) from Causa as A inner join Instituicao as B on A.id = B.causaId where B.cidadeId = ?1 group by A.id order by count(A.id) desc",
            countQuery = "select A, count(A.id) from Causa as A inner join Instituicao as B on A.id = B.causaId where B.cidadeId = ?1 group by A.id order by count(A.id) desc")
    Page<Causa> findActiveCausasByCidadeId(long cidadeId, Pageable pageable);

    @Query(value = "select A, count(A.id) from Causa as A inner join Instituicao as B on A.id = B.causaId where lower(B.nome) like lower(concat('%',?1,'%')) group by A.id order by count(A.id) desc")
    Page<Causa> findActiveCausasByNomeInstituicao(String nomeCidade, Pageable pageable);

    @Query(value = "select A, count(A.id) from Causa as A inner join Instituicao as B on A.id = B.causaId where B.cidadeId = ?1 and lower(B.nome) like lower(concat('%',?2,'%')) group by A.id order by count(A.id) desc")
    Page<Causa> findActiveCausasByCidadeIdAndNomeInstituicao(long cidadeId, String nomeCidade, Pageable pageable);
}
