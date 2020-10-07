package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.entity.ApiErrorEntity;
import com.sylleryum.meajudaaajudar.exception.ResourceAlreadyExistsException;
import com.sylleryum.meajudaaajudar.exception.ResourceBadRequestException;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;
import java.util.UUID;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiErrorEntity> handleResourceNotFoundException(
            ResourceNotFoundException e, WebRequest webRequest) {

        return new ResponseEntity<>(new ApiErrorEntity(e.getTraceId(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<ApiErrorEntity> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException e, WebRequest webRequest) {

        return new ResponseEntity<>(new ApiErrorEntity(e.getTraceId(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public final ResponseEntity<ApiErrorEntity> handleResourceBadRequestException(
            ResourceBadRequestException e, WebRequest webRequest) {

        return new ResponseEntity<>(new ApiErrorEntity(e.getTraceId(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(IlegalTokenException.class)
//    public final ResponseEntity<ApiErrorEntity> handleIlegalTokenException(
//            IlegalTokenException e, WebRequest webRequest) {
//
//        return new ResponseEntity<>(new ApiErrorEntity(e.getTraceId(), e.getMessage()), HttpStatus.FORBIDDEN);
//    }

    //-----------else
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiErrorEntity> handleOtherException(
            Exception e, WebRequest webRequest) {

        String traceId = getTrackId(webRequest);
        logger.error(traceId, e);
        return new ResponseEntity<>(new ApiErrorEntity(traceId, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String getTrackId(WebRequest webRequest) {
        Optional<String> traceId = Optional.ofNullable(webRequest.getHeader("trace-id"));
        return traceId.orElse(UUID.randomUUID().toString());
    }

}
