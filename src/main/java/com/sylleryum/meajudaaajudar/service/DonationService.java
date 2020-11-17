package com.sylleryum.meajudaaajudar.service;

import com.sylleryum.meajudaaajudar.entity.Donation;
import com.sylleryum.meajudaaajudar.entity.Instituicao;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.repository.DonationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final InstituicaoService instituicaoService;
    private static final Logger logger = LoggerFactory.getLogger(DonationRepository.class);

    @Autowired
    public DonationService(DonationRepository donationRepository, InstituicaoService instituicaoService) {
        this.donationRepository = donationRepository;
        this.instituicaoService = instituicaoService;
    }

    public Donation save(Donation donation, String traceId) throws ResourceNotFoundException {
        Optional<Instituicao> instituicaoToAdd = instituicaoService.findById(donation.getInstituicaoId(), traceId);
        if (!instituicaoToAdd.isPresent()) {
            logger.error("TraceId: {} - Instituição não encontrada", traceId);
            throw new ResourceNotFoundException(traceId, "Instituição não encontrada");
        }
        Donation donation1 = new Donation(instituicaoToAdd.get());
        Donation donationAdded = donationRepository.save(donation1);
        logger.debug("TraceId: {} - Doação feita a "+donationAdded.getInstituicaoEntity().getNome()+" em: "+donationAdded.getTime(), traceId);
        return donationAdded;
    }
}
