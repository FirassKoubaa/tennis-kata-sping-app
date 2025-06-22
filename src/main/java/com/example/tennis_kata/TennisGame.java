package com.example.tennis_kata;

public class TennisGame {
    protected int playerAScore = 0;
    protected int playerBScore = 0;
    protected boolean deuce = false;
    protected String advantage = null;
    protected boolean gameEnded = false;

    protected static final int[] POINTS = {0, 15, 30, 40};

    public void play(String sequence) {
        for (char c : sequence.toCharArray()) {
            if (gameEnded) break;
            if (c == 'A') {
                pointWonBy("A");
            } else if (c == 'B') {
                pointWonBy("B");
            }
        }
    }

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

    protected void printScore() {
        if (deuce && advantage == null) {
            System.out.println("Deuce");
        } else if (advantage != null) {
            System.out.println("Advantage Player " + advantage);
        } else {
            System.out.println("Player A : " + POINTS[Math.min(playerAScore, 3)] + " / Player B : " + POINTS[Math.min(playerBScore, 3)]);
        }
    }

    protected void printWinner(String player) {
        System.out.println("Player " + player + " wins the game");
    }

    public static void main(String[] args) {
        TennisGame game = new TennisGame();
        String input = "ABABABABAA";
        if (!input.matches("[aAbB]+")) {
            System.err.println("Erreur : la séquence ne doit contenir que des lettres A ou B.");
            return;
        }
        System.out.println("Simulation pour la séquence : " + input);
        game.play(input.toUpperCase());
    }
}
