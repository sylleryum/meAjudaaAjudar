package com.sylleryum.meajudaaajudar;

import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.entity.Donation;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.repository.CausaRepository;
import com.sylleryum.meajudaaajudar.repository.DonationRepository;
import com.sylleryum.meajudaaajudar.repository.InstituicaoRepository;
import com.sylleryum.meajudaaajudar.service.CidadeService;
import com.sylleryum.meajudaaajudar.service.DonationService;
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

    @Autowired
    DonationRepository donationRepository;

    @Test
    void test() {
        Optional<Instituicao> byId = instituicaoRepository.findById(24l);
        System.out.println();
    }

    @Test
    void em(){

    }

}
