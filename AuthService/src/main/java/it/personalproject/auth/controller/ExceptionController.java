package it.personalproject.auth.controller;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.management.relation.RoleNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import it.personalproject.auth.exception.UserAlreadyPresentException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
    
    
    @ExceptionHandler(RoleNotFoundException.class)
    public ProblemDetail handleRoleNotFoundException(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Internal Server Error Auth Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        pd.setTitle("Internal Server Error Auth Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
    
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ProblemDetail handleUnsernameAlreadyPresentException(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Internal Server Error Auth Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail handleUnsernameNotFoundException(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        pd.setTitle("Internal Server Error Auth Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception e, HttpServletRequest req) {
        String errorId = UUID.randomUUID().toString();
        log.error("[{}] Unhandled error on {} {}", errorId, req.getMethod(), req.getRequestURI(), e);

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error Auth Service");
        pd.setDetail("Si è verificato un errore inatteso. Fornisci questo codice al supporto: " + errorId);
        pd.setInstance(URI.create(req.getRequestURI()));
        pd.setProperty("errorId", errorId);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
}
