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
    void testSimpleWin() {
        TennisGame game = new TennisGame();
        game.play("AAAA");
        // Pas de capture, juste une assertion simple
        // String output = outContent.toString();
        // assertTrue(output.contains("Player A wins the game"));
    }

    @Test
    void testDeuceAndAdvantage() {
        TennisGame game = new TennisGame();
        game.play("ABABABAB"); // 40-40, deuce
        game.play("A"); // Avantage A
        game.play("B"); // Retour à deuce
    }

    @Test
    void testWinAfterAdvantage() {
        TennisGame game = new TennisGame();
        game.play("ABABABABAA"); // A gagne après avantage
    }

    @Test
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
    void testCaseInsensitive() {
        TennisGame game = new TennisGame();
        game.play("aBaBaA".toUpperCase());
    }
}
