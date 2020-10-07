package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.assembler.HATEOASController;
import com.sylleryum.meajudaaajudar.commons.EntityToHATEOAS;
import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.exception.ResourceAlreadyExistsException;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.service.InstituicaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private static Logger logger = LoggerFactory.getLogger(InstituicaoController.class);

    @Autowired
    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll(Pageable pageable,
                                     @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {
        Page<Instituicao> results = instituicaoService.findAll(pageable);
        results.forEach(i -> {
            try {
                EntityToHATEOAS.instituicaoToHATEOAS(i);
            } catch (ResourceNotFoundException e) {

            }
        });

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

    @GetMapping("/search")
    public ResponseEntity<?> findByNome(Pageable pageable, @RequestParam Optional<String> nome,
                                        @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        Page<Instituicao> results;
        if (nome.isPresent()) {
            results = instituicaoService.findByNome(nome.get(), pageable);
            results.forEach(i -> {
                try {
                    EntityToHATEOAS.instituicaoToHATEOAS(i);
                } catch (ResourceNotFoundException e) {

                }
            });
            return new ResponseEntity(results, HttpStatus.OK);

        }
        logger.info("TraceId: {}, Nenhum nome passado na busca", traceId);
        return null;
    }

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
        } catch (ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
