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
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository instituicaoService;
    private final CausaRepository causaRepository;
    private final ContatoRepository contatoRepository;
    private final CidadeService cidadeService;
    private static final Logger logger = LoggerFactory.getLogger(InstituicaoService.class);

    @Autowired
    public InstituicaoService(InstituicaoRepository instituicaoService, CausaRepository causaRepository, ContatoRepository contatoRepository, CidadeService cidadeService) {
        this.instituicaoService = instituicaoService;
        this.causaRepository = causaRepository;
        this.contatoRepository = contatoRepository;
        this.cidadeService = cidadeService;
    }


    public Page<Instituicao> findByNome(String nome, Pageable pageable) {
        return instituicaoService.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Optional<Instituicao> findById(Long aLong, String traceId) throws ResourceNotFoundException {
        Optional<Instituicao> instituicao = instituicaoService.findById(aLong);

        if (instituicao.isPresent()){
            return instituicao;
        } else {
            logger.error("TraceId: {} - Instituição não encontrada", traceId);
            throw new ResourceNotFoundException(traceId,"Instituição não encontrada");
        }

    }

    public Instituicao save(Instituicao instituicaoToAdd, String traceId) throws ResourceAlreadyExistsException {
        Contato contato=null;
        Optional<Causa> causaToAdd = causaRepository.findById(instituicaoToAdd.getCausaId());
        instituicaoToAdd.setCausa(causaToAdd.get());

        Optional<Cidade> cidadeToAdd = cidadeService.findById(instituicaoToAdd.getCidadeId());
        instituicaoToAdd.setCidadeEntity(cidadeToAdd.get());

        logger.debug("TraceId: {}, Solicitação incluir instituicao: {}", traceId, instituicaoToAdd);
        if (instituicaoToAdd.getContato()!=null){
            Optional<Cidade> cidadeToAddToContato = cidadeService.findById(instituicaoToAdd.getContato().getCidadeId());
            instituicaoToAdd.getContato().setCidadeEntity(cidadeToAddToContato.get());
            contato = contatoRepository.save(instituicaoToAdd.getContato());
        }
        instituicaoToAdd.setContato(contato);
        System.out.println();

        try {
            Instituicao addedInstituicao = instituicaoService.save(instituicaoToAdd);
            logger.info("TraceId: {}, Instituicao adicionada: {}", traceId, addedInstituicao);
            return addedInstituicao;
        } catch ( DataIntegrityViolationException e){
            logger.error("TraceId: {}, Instituição já existe", traceId, e);
            throw new ResourceAlreadyExistsException(traceId,"Instituição já existe");
        }

//        System.out.println();
//
//        return null;
    }

    public Iterable<Instituicao> findAll(Sort sort) {
        return instituicaoService.findAll(sort);
    }

    public Page<Instituicao> findAll(Pageable pageable) {
        return instituicaoService.findAll(pageable);
    }



    public <S extends Instituicao> Iterable<S> saveAll(Iterable<S> iterable) {
        return instituicaoService.saveAll(iterable);
    }



    public boolean existsById(Long aLong) {
        return instituicaoService.existsById(aLong);
    }

    public Iterable<Instituicao> findAll() {
        return instituicaoService.findAll();
    }

    public Iterable<Instituicao> findAllById(Iterable<Long> iterable) {
        return instituicaoService.findAllById(iterable);
    }

    public long count() {
        return instituicaoService.count();
    }

    public void deleteById(Long aLong) {
        instituicaoService.deleteById(aLong);
    }

    public void delete(Instituicao instituicao) {
        instituicaoService.delete(instituicao);
    }

    public void deleteAll(Iterable<? extends Instituicao> iterable) {
        instituicaoService.deleteAll(iterable);
    }

    public void deleteAll() {
        instituicaoService.deleteAll();
    }
}
