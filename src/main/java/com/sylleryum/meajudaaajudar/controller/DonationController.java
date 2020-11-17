package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.entity.Donation;
import com.sylleryum.meajudaaajudar.entity.Test;
import com.sylleryum.meajudaaajudar.exception.ResourceAlreadyExistsException;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/registrardoacao")
public class DonationController {

    DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> save(@RequestBody Donation donationToAdd,
                                  @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) throws ResourceNotFoundException {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        return new ResponseEntity<>(donationService.save(donationToAdd, traceId), HttpStatus.CREATED);
    }
}
