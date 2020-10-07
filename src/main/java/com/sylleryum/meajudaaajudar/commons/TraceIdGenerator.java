package com.sylleryum.meajudaaajudar.commons;

import java.util.Optional;
import java.util.UUID;

public class TraceIdGenerator {

    public static String generateTraceId(Optional<String> traceId){
        return traceId.orElse(UUID.randomUUID().toString());
    }

}
