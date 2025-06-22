package com.example.tennis_kata;

import java.util.Locale;

public class TennisGame {
    protected int playerAScore = 0;
    protected int playerBScore = 0;
    protected boolean deuce = false;
    protected String advantage = null;
    protected boolean gameEnded = false;

    protected static final int[] POINTS = {0, 15, 30, 40};

    /**
     * Joue une séquence de points pour le jeu de tennis.
     * @param sequence chaîne composée de 'A' ou 'B' (insensible à la casse)
     */
    public void play(String sequence) {
        if (sequence == null || !sequence.matches("[aAbB]+")) {
            System.err.println("Erreur : la séquence ne doit contenir que des lettres A ou B.");
            return;
        }
        for (char c : sequence.toUpperCase(Locale.ROOT).toCharArray()) {
            if (gameEnded) break;
            if (c == 'A') {
                pointWonBy("A");
            } else if (c == 'B') {
                pointWonBy("B");
            }
        }
    }

    /**
     * Gère le gain d'un point par un joueur.
     * @param player "A" ou "B"
     */
    protected void pointWonBy(String player) {
        if (deuce) {
            if (advantage == null) {
                advantage = player;
                printScore();
            } else if (advantage.equals(player)) {
                printWinner(player);
                gameEnded = true;
            } else {
                advantage = null;
                printScore();
            }
        } else {
            if (player.equals("A")) playerAScore++;
            else playerBScore++;

            if (playerAScore >= 3 && playerBScore >= 3) {
                if (playerAScore == playerBScore) {
                    deuce = true;
                    printScore();
                } else {
                    printScore();
                }
            } else if (playerAScore >= 4 && playerAScore - playerBScore >= 2) {
                printWinner("A");
                gameEnded = true;
            } else if (playerBScore >= 4 && playerBScore - playerAScore >= 2) {
                printWinner("B");
                gameEnded = true;
            } else {
                printScore();
            }
        }
    }

    /**
     * Affiche le score courant du jeu.
     */
    protected void printScore() {
        if (deuce && advantage == null) {
            System.out.println("Deuce");
        } else if (advantage != null) {
            System.out.println("Advantage Player " + advantage);
        } else {
            System.out.println("Player A : " + POINTS[Math.min(playerAScore, 3)] + " / Player B : " + POINTS[Math.min(playerBScore, 3)]);
        }
    }

    /**
     * Affiche le gagnant du jeu.
     * @param player "A" ou "B"
     */
    protected void printWinner(String player) {
        System.out.println("Player " + player + " wins the game");
    }

    public static void main(String[] args) {
        TennisGame game = new TennisGame();
        String input = "ABABABABAA";
        System.out.println("Simulation pour la séquence : " + input);
        game.play(input);
    }
}
