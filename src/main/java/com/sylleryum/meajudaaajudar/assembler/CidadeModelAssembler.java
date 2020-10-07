package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.controller.CidadeController;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeModelAssembler implements RepresentationModelAssembler<Cidade, EntityModel<Cidade>> {


    @Override
    public EntityModel<Cidade> toModel(Cidade entity) {

        //EntityModel<Cidade>

//        try {
//            return EntityModel.of(entity,
//                    linkTo(methodOn(CidadeController.class).findById(entity.getId(), null))
//                            .withSelfRel())
//                    .add(linkTo(methodOn(CidadeController.class).findAll(null, null))
//                                    .withSelfRel());
//        } catch (ResourceNotFoundException e) {
//
//        }
        return null;
    }


}
