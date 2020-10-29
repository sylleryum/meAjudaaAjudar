package com.sylleryum.meajudaaajudar.service;

import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.repository.CausaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CausaService {

    CausaRepository causaRepository;
    private static final Logger logger = LoggerFactory.getLogger(CausaService.class);

    @Autowired
    public CausaService(CausaRepository causaRepository) {
        this.causaRepository = causaRepository;
    }

    public Page<Causa> findAllActiveCausas(Pageable pageable, String traceId) {
        return causaRepository.findAllActiveCausas(pageable);
    }

    public Page<Causa> search(Optional<Long> cidadeId, Optional<String> nomeInstituicao, Pageable pageable, String traceId){
        if (cidadeId.isPresent() && nomeInstituicao.isPresent()){
            return findActiveCausasByCidadeIdAndNomeInstituicao(cidadeId.get(), nomeInstituicao.get(), pageable, traceId);
        }

        if (cidadeId.isPresent() && !nomeInstituicao.isPresent()){
            return findActiveCausasByCidadeId(cidadeId.get(), pageable, traceId);
        }

        if (!cidadeId.isPresent() && nomeInstituicao.isPresent()){
            return findActiveCausasByNomeInstituicao(nomeInstituicao.get(), pageable, traceId);
        }
        logger.info("TraceId: {}, no parameter passed, returning AllActiveCausas", traceId);
        return findAllActiveCausas(pageable, traceId);
    }

    public Page<Causa> findActiveCausasByCidadeId(long cidadeId, Pageable pageable, String traceId) {
        logger.info("TraceId: {}, cidadeId search", traceId);
        Page<Causa> activeCausasByInstituicao = causaRepository.findActiveCausasByCidadeId(cidadeId, pageable);
        return activeCausasByInstituicao;
    }

    public Page<Causa> findActiveCausasByNomeInstituicao(String nomeCidade, Pageable pageable, String traceId) {
        logger.info("TraceId: {}, nomeInstituicao search", traceId);
        return causaRepository.findActiveCausasByNomeInstituicao(nomeCidade, pageable);
    }

    public Page<Causa> findActiveCausasByCidadeIdAndNomeInstituicao(long cidadeId, String nomeCidade, Pageable pageable, String traceId) {
        logger.info("TraceId: {}, cidadeId and NomeInstituicao search", traceId);
        return causaRepository.findActiveCausasByCidadeIdAndNomeInstituicao(cidadeId, nomeCidade, pageable);
    }
}
