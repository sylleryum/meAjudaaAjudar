package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.entity.Causa;
import com.sylleryum.meajudaaajudar.service.CausaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/causas")
public class CausaController {

    CausaService causaService;

    @Autowired
    public CausaController(CausaService causaService) {
        this.causaService = causaService;
    }

    @GetMapping("/ativas")
    public ResponseEntity<?> findByAllAtivas(Pageable pageable,
                                          @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);
        Page<Causa> results = causaService.findAllActiveCausas(pageable, traceId);
        return new ResponseEntity(results, HttpStatus.OK);

    }

    //findActiveCausasByInstituicao
    @GetMapping("ativas/instituicoes/search")
    public ResponseEntity<?> findByAtivasOfId(Pageable pageable,
                                              @RequestHeader(value = "trace-id") Optional<String> traceIdHeader,
                                              @RequestParam Optional<Long> cidade,
                                              @RequestParam Optional<String> instituicao) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        Page<Causa> results = causaService.search(cidade, instituicao, pageable, traceId);
        return new ResponseEntity(results, HttpStatus.OK);

    }
}
