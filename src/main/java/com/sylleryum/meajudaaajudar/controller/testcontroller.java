package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.security.ApiUserEntity;
import com.sylleryum.meajudaaajudar.security.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/v1/register")
public class testcontroller {

    final ApiUserRepository apiUserRepository;

    public testcontroller(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    @GetMapping
    ResponseEntity<String> root(Pageable pageable){

        Optional<ApiUserEntity> id = apiUserRepository.findById(2l);
        id.get().setEnabled(true);
        apiUserRepository.save(id.get());

        RepresentationModel model = new RepresentationModel();

        return ResponseEntity.ok("ok");
    }
}
