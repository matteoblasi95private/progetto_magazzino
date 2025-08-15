package it.personalproject.clienti.controller;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error Clienti Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
}
