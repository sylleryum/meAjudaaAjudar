package com.sylleryum.meajudaaajudar.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class RootController {

    @GetMapping(value = {"/v1/", "/v1", "/", ""})
    ResponseEntity<RepresentationModel> root(Pageable pageable){

        RepresentationModel model = new RepresentationModel();

        model.add(linkTo(methodOn(RootController.class).root(pageable)).withSelfRel());
        model.add(linkTo(methodOn(EstadoController.class).findAll(pageable, null)).withRel("estados"));
        model.add(linkTo(methodOn(CidadeController.class).findAll(pageable, null)).withRel("cidades"));

        return ResponseEntity.ok(model);
    }
}
