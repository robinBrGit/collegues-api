package dev.br.web.collegueapi.controller;

import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    // la méthode handleConflict est exécutée lorsqu'un contrôleur émet une exception présente
    // dans la liste définie par l'annotation @ExceptionHandler
    @ExceptionHandler(value = { CollegueNonTrouveException.class })
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(value = { CollegueInvalideException.class })
    protected ResponseEntity<Object> handleConflictAjouter(CollegueInvalideException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
