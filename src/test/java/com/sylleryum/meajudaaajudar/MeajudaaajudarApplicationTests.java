package com.sylleryum.meajudaaajudar;

import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.repository.CausaRepository;
import com.sylleryum.meajudaaajudar.repository.InstituicaoRepository;
import com.sylleryum.meajudaaajudar.service.CidadeService;
import com.sylleryum.meajudaaajudar.service.InstituicaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MeajudaaajudarApplicationTests {

    @Autowired
    CidadeService cidadeService;
    @Autowired
    InstituicaoRepository instituicaoRepository;
    @Autowired
    InstituicaoService instituicaoService;
    @Autowired
    CausaRepository causaRepository;

    @Test
    void test() {
        Optional<Long> cidadeId = Optional.of(4174l);
        Optional<String> nomeInstituicao = Optional.empty();
        Optional<Long[]> causaIds = Optional.empty();

        Page<Instituicao> resultsCidadeId = instituicaoService.findByCidadeNomeCausa(cidadeId, nomeInstituicao, causaIds, PageRequest.of(0, 20), null);
        nomeInstituicao = Optional.of("cepa");

        Page<Instituicao> resultsnomeInstituicao = instituicaoService.findByCidadeNomeCausa(cidadeId, nomeInstituicao, causaIds, PageRequest.of(0, 20), null);
        causaIds = Optional.of(new Long[]{4l});

        Page<Instituicao> resultsnomeInstituicaoCausas = instituicaoService.findByCidadeNomeCausa(cidadeId, nomeInstituicao, causaIds, PageRequest.of(0, 20), null);

        Page<Instituicao> resultsCidadeIdCausas = instituicaoService.findByCidadeNomeCausa(cidadeId, nomeInstituicao, causaIds, PageRequest.of(0, 20), null);

        System.out.println();

    }

    @Test
    void em(){

    }

}
