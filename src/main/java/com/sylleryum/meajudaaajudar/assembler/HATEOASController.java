package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface HATEOASController {

//    public ResponseEntity<?> findAll(Pageable pageable){return null;}
//    public ResponseEntity<?> findById(Long id){return null;}

    ResponseEntity<?> findAll(Pageable pageable, Optional<String> traceIdHeader) throws ResourceNotFoundException;
    ResponseEntity<?> findById(Long id, Optional<String> traceIdHeader) throws ResourceNotFoundException;
}
