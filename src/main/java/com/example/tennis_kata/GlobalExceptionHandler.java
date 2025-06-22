package com.example.tennis_kata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire global des exceptions pour l'application Tennis Kata.
 * <p>
 * Cette classe intercepte les exceptions de type {@link ResponseStatusException}
 * et retourne une r��ponse HTTP appropriée avec un message d'erreur au format JSON.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Gère les exceptions de type {@link ResponseStatusException} et retourne une réponse HTTP
     * contenant le message d'erreur et le code de statut correspondant.
     *
     * @param ex l'exception interceptée
     * @return une {@link ResponseEntity} contenant le message d'erreur au format JSON
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}
