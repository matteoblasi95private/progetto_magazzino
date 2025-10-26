package it.personalproject.clienti.controller;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleConstraintViolationException(DataIntegrityViolationException e, HttpServletRequest req) {
    	return buildProblemDetail(e, "3", req, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMalformedRequest(MethodArgumentNotValidException e, HttpServletRequest req) {
    	return buildProblemDetail(e, "1", req, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception e, HttpServletRequest req) {
    	return buildProblemDetail(e, "2", req, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private ProblemDetail buildProblemDetail(Exception e, String errorId, HttpServletRequest req, HttpStatus status) {
    	
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle("Internal Server Error Clienti Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    	
    }
}
