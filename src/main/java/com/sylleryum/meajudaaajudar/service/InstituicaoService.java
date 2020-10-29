package com.sylleryum.meajudaaajudar.service;

import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.entity.Contato;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.exception.ResourceAlreadyExistsException;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.repository.CausaRepository;
import com.sylleryum.meajudaaajudar.repository.ContatoRepository;
import com.sylleryum.meajudaaajudar.repository.InstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;
    private final CausaRepository causaRepository;
    private final ContatoRepository contatoRepository;
    private final CidadeService cidadeService;
    private static final Logger logger = LoggerFactory.getLogger(InstituicaoService.class);

    @Autowired
    public InstituicaoService(InstituicaoRepository instituicaoRepository, CausaRepository causaRepository, ContatoRepository contatoRepository, CidadeService cidadeService) {
        this.instituicaoRepository = instituicaoRepository;
        this.causaRepository = causaRepository;
        this.contatoRepository = contatoRepository;
        this.cidadeService = cidadeService;
    }


    public Page<Instituicao> findByNome(String nome, Pageable pageable) {
        return instituicaoRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Instituicao> findByCidadeId(long cidadeId, Pageable pageable, String traceId) {
        return instituicaoRepository.findByCidadeId(cidadeId, pageable);
    }

    public Optional<Instituicao> findById(Long aLong, String traceId) throws ResourceNotFoundException {
        Optional<Instituicao> instituicao = instituicaoRepository.findById(aLong);

        if (instituicao.isPresent()) {
            return instituicao;
        } else {
            logger.error("TraceId: {} - Instituição não encontrada", traceId);
            throw new ResourceNotFoundException(traceId, "Instituição não encontrada");
        }

    }

    public Page<Instituicao> findByCidadeIdAndCausaIds(long cidadeId, Long[] causaIds, Pageable pageable, String traceId) {
        return instituicaoRepository.findByCidadeIdAndCausaIds(cidadeId, causaIds, pageable);
    }

    public Page<Instituicao> findByCidadeNomeCausa(Optional<Long> cidadeId, Optional<String> nomeInstituicao, Optional<Long[]> causaIds, Pageable pageable, String traceId) {

        //cidade only search
        if (cidadeId.isPresent() && !causaIds.isPresent()) {
            logger.info("TraceId: {}, Cidade only search", traceId);
            return instituicaoRepository.findByCidadeId(cidadeId.get(), pageable);
        }
        //cidade and causas search
        if (cidadeId.isPresent() && causaIds.isPresent()) {
            logger.info("TraceId: {}, Cidade and causas search", traceId);
            return instituicaoRepository.findByCidadeIdAndCausaIds(cidadeId.get(), causaIds.get(), pageable);
        }

        //cidade only search
        if (nomeInstituicao.isPresent() && !causaIds.isPresent()) {
            logger.info("TraceId: {}, Nome instituicao search", traceId);
            return instituicaoRepository.findByNomeContainingIgnoreCase(nomeInstituicao.get(), pageable);
        }

        //cidade and causas search
        if (nomeInstituicao.isPresent() && causaIds.isPresent()) {
            logger.info("TraceId: {}, Nome instituicao search and causas search", traceId);
            return instituicaoRepository.findByNomeContainingIgnoreCase(nomeInstituicao.get(), pageable);
        }
        logger.warn("TraceId: {}, Nenhum nome passado na busca", traceId);
        return instituicaoRepository.findAll(pageable);
    }


    public Instituicao save(Instituicao instituicaoToAdd, String traceId) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        Contato contato = null;
        Optional<Causa> causaToAdd = causaRepository.findById(instituicaoToAdd.getCausaId());
        instituicaoToAdd.setCausa(causaToAdd.get());

        Optional<Cidade> cidadeToAdd = cidadeService.findById(instituicaoToAdd.getCidadeId(), traceId);
        instituicaoToAdd.setCidadeEntity(cidadeToAdd.get());

        logger.debug("TraceId: {}, Solicitação incluir instituicao: {}", traceId, instituicaoToAdd);
        if (instituicaoToAdd.getContato() != null) {
            Optional<Cidade> cidadeToAddToContato = cidadeService.findById(instituicaoToAdd.getContato().getCidadeId(), traceId);
            instituicaoToAdd.getContato().setCidadeEntity(cidadeToAddToContato.get());
            contato = contatoRepository.save(instituicaoToAdd.getContato());
        }
        instituicaoToAdd.setContato(contato);
        System.out.println();

        try {
            Instituicao addedInstituicao = instituicaoRepository.save(instituicaoToAdd);
            logger.info("TraceId: {}, Instituicao adicionada: {}", traceId, addedInstituicao);
            return addedInstituicao;
        } catch (DataIntegrityViolationException e) {
            logger.error("TraceId: {}, Instituição já existe", traceId, e);
            throw new ResourceAlreadyExistsException(traceId, "Instituição já existe");
        }

//        System.out.println();
//
//        return null;
    }

    public Iterable<Instituicao> findAll(Sort sort) {
        return instituicaoRepository.findAll(sort);
    }

    public Page<Instituicao> findAll(Pageable pageable) {
        return instituicaoRepository.findAll(pageable);
    }


    public <S extends Instituicao> Iterable<S> saveAll(Iterable<S> iterable) {
        return instituicaoRepository.saveAll(iterable);
    }


    public boolean existsById(Long aLong) {
        return instituicaoRepository.existsById(aLong);
    }

    public Iterable<Instituicao> findAll() {
        return instituicaoRepository.findAll();
    }

    public Iterable<Instituicao> findAllById(Iterable<Long> iterable) {
        return instituicaoRepository.findAllById(iterable);
    }

    public long count() {
        return instituicaoRepository.count();
    }

    public void deleteById(Long aLong) {
        instituicaoRepository.deleteById(aLong);
    }

    public void delete(Instituicao instituicao) {
        instituicaoRepository.delete(instituicao);
    }

    public void deleteAll(Iterable<? extends Instituicao> iterable) {
        instituicaoRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        instituicaoRepository.deleteAll();
    }

    public Page<Instituicao> findAll(Specification<Instituicao> specification, Pageable pageable, String traceId) {
        logger.info("TraceId: {}, Instituicao search: "+specification, traceId);

        return instituicaoRepository.findAll(specification, pageable);
    }
}
