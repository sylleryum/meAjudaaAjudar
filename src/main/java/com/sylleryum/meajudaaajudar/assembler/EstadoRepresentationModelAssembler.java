package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.commons.SimpleIdentifiableRepresentationModelAssembler;
import com.sylleryum.meajudaaajudar.controller.EstadoController;
import com.sylleryum.meajudaaajudar.entity.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Estado> {

    public EstadoRepresentationModelAssembler() {
        super(EstadoController.class);
    }
}
