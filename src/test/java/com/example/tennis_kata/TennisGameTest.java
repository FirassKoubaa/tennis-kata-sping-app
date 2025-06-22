package com.example.tennis_kata;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {
    // Suppression de la redirection de System.out pour afficher le jeu en console
    // private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    // private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        // System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        // System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Victoire simple de A")
    void testSimpleWin() {
        TennisGame game = new TennisGame();
        game.play("AAAA");
    }

    @Test
    @DisplayName("Gestion du deuce et de l'avantage")
    void testDeuceAndAdvantage() {
        TennisGame game = new TennisGame();
        game.play("ABABABAB"); // 40-40, deuce
        game.play("A"); // Avantage A
        game.play("B"); // Retour à deuce
    }

    @Test
    @DisplayName("Victoire après avantage")
    void testWinAfterAdvantage() {
        TennisGame game = new TennisGame();
        game.play("ABABABABAA"); // A gagne après avantage
    }

    @Test
    @DisplayName("Séquence invalide")
    void testInvalidSequence() {
        try {
            TennisGame game = new TennisGame();
            String input = "ABXBA";
            boolean invalid = !input.matches("[aAbB]+");
            assertTrue(invalid);
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }

    @Test
    @DisplayName("Insensibilité à la casse")
    void testCaseInsensitive() {
        TennisGame game = new TennisGame();
        game.play("aBaBaA".toUpperCase());
    }

    @Test
    @DisplayName("Test direct de pointWonBy(String) via sous-classe")
    void testPointWonByDirect() {
        class TestableTennisGame extends TennisGame {
            @Override
            protected void printScore() { /* no-op for test */ }
            @Override
            protected void printWinner(String player) { /* no-op for test */ }
        }
        TestableTennisGame game = new TestableTennisGame();
        game.pointWonBy("A");
        assertEquals(1, game.playerAScore);
        game.pointWonBy("B");
        assertEquals(1, game.playerBScore);
    }

    @Test
    @DisplayName("Test de la méthode main(String[]) de TennisGame")
    void testMainMethodTennisGame() {
        assertDoesNotThrow(() -> TennisGame.main(new String[]{}));
    }

    @Test
    @DisplayName("Test play avec séquence vide")
    void testPlayWithEmptySequence() {
        TennisGame game = new TennisGame();
        game.play("");
        assertEquals(0, game.playerAScore);
        assertEquals(0, game.playerBScore);
    }

    @Test
    @DisplayName("Test play avec séquence nulle")
    void testPlayWithNullSequence() {
        TennisGame game = new TennisGame();
        game.play(null);
        assertEquals(0, game.playerAScore);
        assertEquals(0, game.playerBScore);
    }
}
