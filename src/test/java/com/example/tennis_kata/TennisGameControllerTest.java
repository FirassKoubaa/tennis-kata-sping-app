package com.example.tennis_kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TennisGameController.class)
class TennisGameControllerTest {

    private static final Logger log = LoggerFactory.getLogger(TennisGameControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void displayGamePlayDetails() {
        System.out.println("--- Affichage console TennisGame (avant chaque test TennisGameControllerTest) ---");
        TennisGame game = new TennisGame();
        game.play("ABABAA");
        System.out.println("--- Fin affichage console ---");
    }

    @Test
    void testScoreEndpointSuccess() throws Exception {
        try {
            mockMvc.perform(get("/api/tennis/score/ABABAA"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").value("Player A : 15 / Player B : 0"))
                    .andExpect(jsonPath("$[5]").value("Player A wins the game"));
        } catch (Throwable t) {
            log.error("Test testScoreEndpointSuccess failed", t);
            throw t;
        }
    }

    @Test
    void testScoreEndpointDeuceAndAdvantage() throws Exception {
        try {
            mockMvc.perform(get("/api/tennis/score/ABABABAB"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[7]").value("Deuce"));
        } catch (Throwable t) {
            t.printStackTrace();
            log.error("Test testScoreEndpointDeuceAndAdvantage failed", t);
            throw t;
        }
    }

    @Test
    void testScoreEndpointInvalidSequence() throws Exception {
        try {
            mockMvc.perform(get("/api/tennis/score/ABXBA"))
                    .andExpect(status().isBadRequest())
                    .andExpect(status().reason("La séquence ne doit contenir que des lettres A ou B."));
        } catch (Throwable t) {
            t.printStackTrace();
            log.error("Test testScoreEndpointInvalidSequence failed", t);
            throw t;
        }
    }

    @Test
    void testGamePlayConsoleOutput() {
        // Ce test affiche le détail du jeu dans la console
        TennisGame game = new TennisGame();
        System.out.println("--- Affichage console TennisGame depuis TennisGameControllerTest ---");
        game.play("ABABAA");
        System.out.println("--- Fin affichage console ---");
    }
}
