package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.assembler.HATEOASController;
import com.sylleryum.meajudaaajudar.commons.EntityToHATEOAS;
import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.exception.ResourceAlreadyExistsException;
import com.sylleryum.meajudaaajudar.exception.ResourceBadRequestException;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.repository.InstituicaoRepository;
import com.sylleryum.meajudaaajudar.service.InstituicaoService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/instituicoes")
public class InstituicaoController implements HATEOASController {

    private final InstituicaoService instituicaoService;
    private final InstituicaoRepository instituicaoRepository;
    private static Logger logger = LoggerFactory.getLogger(InstituicaoController.class);

    @Autowired
    public InstituicaoController(InstituicaoService instituicaoService, InstituicaoRepository instituicaoRepository) {
        this.instituicaoService = instituicaoService;
        this.instituicaoRepository = instituicaoRepository;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll(Pageable pageable,
                                     @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) throws ResourceNotFoundException {
        Page<Instituicao> results = instituicaoService.findAll(pageable);
        EntityToHATEOAS.instituicoesToHATEOAS(results);

        return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id,
                                      @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) throws ResourceNotFoundException {

        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);


        Optional<Instituicao> results = instituicaoService.findById(id, traceId);
        EntityToHATEOAS.instituicaoToHATEOAS(results.get());
        return new ResponseEntity(results.get(), HttpStatus.OK);


    }

    /*@GetMapping("/search")
    public ResponseEntity<?> findByNome(Pageable pageable,
                                        @RequestParam Optional<String> nome,
                                        @RequestParam Optional<Long[]> causas,
                                        @RequestParam Optional<Long> cidade,
                                        @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) throws ResourceNotFoundException, ResourceBadRequestException {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        Page<Instituicao> results = null;
        //nome only search
        if (nome.isPresent()) {
            logger.info("TraceId: {}, Nome search", traceId);
            results = instituicaoService.findByNome(nome.get(), pageable);
        }

        //cidade only search
        if (cidade.isPresent()) {
            logger.info("TraceId: {}, Cidade search", traceId);
            results = instituicaoService.findByCidadeId(cidade.get(), pageable, traceId);
        }

        //cidade and causas search
        if (cidade.isPresent() && causas.isPresent()) {
            logger.info("TraceId: {}, Cidade and Causas search", traceId);
            results = instituicaoService.findByCidadeIdAndCausaIds(cidade.get(), causas.get(), pageable, traceId);

        }

        if (results != null && results.getTotalElements() > 0) {
            EntityToHATEOAS.instituicoesToHATEOAS(results);
            return new ResponseEntity(results, HttpStatus.OK);
        }

        logger.warn("TraceId: {}, Nenhum nome passado na busca", traceId);
//        throw new ResourceBadRequestException("Nenhum parametro passado na busca " +
//                "(parametros disponivels: nome, causas, cidade)", traceId);
        return findAll(pageable, Optional.ofNullable(traceId));
    }*/

    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> save(@Valid @RequestBody Instituicao instituicaoToAdd,
                                  @RequestHeader(value = "trace-id") Optional<String> traceIdHeader,
                                  BindingResult bindingResult) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        System.out.println();
        if (bindingResult.hasErrors()) {
            List i = bindingResult.getAllErrors();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(instituicaoService.save(instituicaoToAdd, traceId), HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException | ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @Join(path = "cidadeEntity", alias = "c")
            @Join(path = "causa", alias = "d")
            @And({
                    @Spec(path = "c.id", params = "cidade", spec = Equal.class),
                    @Spec(path = "d.id", params = "causa", spec = In.class),
                    @Spec(path = "nome", spec = LikeIgnoreCase.class)
            })
                    Specification<Instituicao> customerSpec,
            Pageable pageable) throws ResourceNotFoundException {

        String traceId = TraceIdGenerator.generateTraceId(Optional.empty());

        Page<Instituicao> results = instituicaoService.findAll(customerSpec, pageable, traceId);

        if (results != null && results.getTotalElements() > 0) {
            EntityToHATEOAS.instituicoesToHATEOAS(results);
        }
        System.out.println();

        return new ResponseEntity(results, HttpStatus.OK);
    }

}
