package src.wilhelmsen.oving.oving4;

import java.util.Random;

/**
 * Created by Harald Wilhelmsen on 8/22/2014.
 */
public class Oppgave3_terningSpill {

	/*
	 * Oppgave 3:
	 *  Terningspill
	 *  Regler:
	 *      De kaster annenhver gang
	 *      Øyne på terning er poeng. Summeres fortløpende.
	 *      Kaster man 1 mister man alle poengene
	 */

    public static void main(String[] args) {
        oppgave3();
    }

    private static void oppgave3() {
        int rounds = 0;
        Random rng = new Random();

        Player p1 = new Player();
        Player p2 = new Player();

        int winThreshold = 100;

        while(true) {
            p1.throwDice(rng);
            p2.throwDice(rng);
            if(p1.hasWon(winThreshold)) {
                System.out.println("Player 1 wins with " + p1 + " points.");
                System.out.println("Player 2 had " + p2 + " points at game end.");
                break;
            }

            if(p2.hasWon(winThreshold)) {
                System.out.println("Player 2 wins with " + p2 + " points.");
                System.out.println("Player 1 had " + p1 + " points at game end.");
                break;
            }
            rounds++;
        }
        System.out.println("The game lasted for " + rounds + " turns.");
    }

    private static void oppgave3Efficient() {
        int i = 0; // iterations

        int p1 = 0;
        int p2 = 0;

        Random rng = new Random();

        while(true) {
            int d = rng.nextInt(6) + 1;
            System.out.println("P1 rolls " + d + " with " + p1 + " points.");
            if(d == 1) p1 = 0;
            else p1 += d;

            d = rng.nextInt(6) + 1;
            System.out.println("P2 rolls " + d + " with " + p2 + " points.");
            if(d == 1) p2 = 0;
            else p2 += d;

            if(p1 >= 100) {
                System.out.println("Player 1 wins with " + p1 + " points.");
                System.out.println("Player 2 had " + p2 + " points at game end.");
                System.out.println("The game lasted for " + i + " turns.");
                break;
            }
            if(p2 >= 100) {
                System.out.println("Player 2 wins with " + p2 + " points.");
                System.out.println("Player 1 had " + p1 + " points at game end.");
                System.out.println("The game lasted for " + i + " turns.");
                break;
            }
            i++;
        }
    }
}

