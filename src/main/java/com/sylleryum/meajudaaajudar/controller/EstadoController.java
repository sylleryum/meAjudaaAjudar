package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.commons.EntityToHATEOAS;
import com.sylleryum.meajudaaajudar.entity.Estado;
import com.sylleryum.meajudaaajudar.assembler.HATEOASController;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "/v1/estados")
public class EstadoController implements HATEOASController {

    private final EstadoService estadoService;
    //private final EstadoRepresentationModelAssembler estadoRepresentationModelAssembler;
    //private final PagedResourcesAssembler<Estado> estadoPagedResourcesAssembler;
    //private final EstadoModelAssembler estadoModelAssembler;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll(Pageable pageable,
                                     Optional<String> traceIdHeader) {

        Page<Estado> results = estadoService.findAll(pageable);
        results.forEach(i -> {
            try {
                EntityToHATEOAS.estadoToHATEOAS(i);
            } catch (ResourceNotFoundException e) {
            }
        });

        return new ResponseEntity(results, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id,
                                      Optional<String> traceIdHeader) throws ResourceNotFoundException {
        Optional<Estado> results = estadoService.findById(id);
        if (results.isPresent()) {
            EntityToHATEOAS.estadoToHATEOAS(results.get());
            return new ResponseEntity(results.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/search")
    public ResponseEntity<?> findByNome(Pageable pageable, @RequestParam Optional<String> nome) {
        System.out.println();
        Page<Estado> results;
        if (nome.isPresent()) {
            results = estadoService.findByUf(nome.get(), pageable);
            if (results.getTotalElements() == 0) {
                results = estadoService.findByNome(nome.get(), pageable);
            }
            results.forEach(i -> {
                try {
                    EntityToHATEOAS.estadoToHATEOAS(i);
                } catch (ResourceNotFoundException e) {
                }
            });
            return new ResponseEntity(results, HttpStatus.OK);

        }
        System.out.println();
        return null;
    }
}
