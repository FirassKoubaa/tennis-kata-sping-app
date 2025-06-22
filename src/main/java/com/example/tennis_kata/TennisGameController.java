package com.example.tennis_kata;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Validated
@RestController
@RequestMapping("/api/tennis")
public class TennisGameController {
    /**
     * Calcule et retourne le score du jeu de tennis pour une séquence donnée.
     * @param sequence chaîne composée de 'A' ou 'B' (insensible à la casse)
     * @return liste des scores après chaque point
     */
    @Operation(summary = "Calcule le score du jeu de tennis pour une séquence de points")
    @GetMapping("/score/{sequence}")
    public List<String> computeScore(@PathVariable String sequence) {
        if (sequence == null || !sequence.matches("[aAbB]+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La séquence ne doit contenir que des lettres A ou B.");
        }
        List<String> result = new ArrayList<>();
        TennisGame game = new TennisGame() {
            @Override
            protected void printScore() {
                if (deuce && advantage == null) {
                    result.add("Deuce");
                } else if (advantage != null) {
                    result.add("Advantage Player " + advantage);
                } else {
                    result.add("Player A : " + POINTS[Math.min(playerAScore, 3)] + " / Player B : " + POINTS[Math.min(playerBScore, 3)]);
                }
            }
            @Override
            protected void printWinner(String player) {
                result.add("Player " + player + " wins the game");
            }
        };
        game.play(sequence);
        return result;
    }
}
