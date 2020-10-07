package com.sylleryum.meajudaaajudar.commons;

import com.sylleryum.meajudaaajudar.assembler.HATEOASBuilder;
import com.sylleryum.meajudaaajudar.controller.CidadeController;
import com.sylleryum.meajudaaajudar.controller.EstadoController;
import com.sylleryum.meajudaaajudar.controller.InstituicaoController;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.entity.Estado;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;


public class EntityToHATEOAS {

    public static void estadoToHATEOAS(Estado estado) throws ResourceNotFoundException {
        HATEOASBuilder.linksBuilder(estado, EstadoController.class, "estados");
        HATEOASBuilder.linksBuilderNestedEntity(estado.getCidades(), CidadeController.class, "cidades");
    }

    public static void cidadeToHATEOAS(Cidade cidade) throws ResourceNotFoundException {
        HATEOASBuilder.linksBuilder(cidade, CidadeController.class, "cidades");
        HATEOASBuilder.linksBuilderNestedEntity(cidade.getInstituicoes(), CidadeController.class, "instituições");
    }

    public static void instituicaoToHATEOAS(Instituicao instituicao) throws ResourceNotFoundException {
        HATEOASBuilder.linksBuilder(instituicao, InstituicaoController.class, "cidades");
        //HATEOASBuilder.linksBuilderNestedEntity(instituicao.getInstituicoes(), CidadeController.class, "instituições");
    }
}
