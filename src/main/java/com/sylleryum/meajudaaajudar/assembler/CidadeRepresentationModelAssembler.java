package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.commons.SimpleIdentifiableRepresentationModelAssembler;
import com.sylleryum.meajudaaajudar.controller.CidadeController;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import org.springframework.stereotype.Component;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class CidadeRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Cidade> {


    public CidadeRepresentationModelAssembler() {
        super(CidadeController.class);
    }
}
