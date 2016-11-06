package src.wilhelmsen.oving.oving4;

import java.util.Random;

/**
 * Created by Harald Wilhelmsen on 9/15/2014.
 */
public class Player {

    private int points;

    public Player() {
        points = 0;
    }

    public boolean hasWon(int threshold) {
        return points > threshold;
    }

    public void throwDice(Random rng) {
        int d = rng.nextInt(6) + 1;
        System.out.print("Rolls for " + d);
        if(d == 1) points = 0;
        else points += d;
        System.out.println(". Now has " + points + " points.");
    }

    @Override
    public String toString() {
        return String.valueOf(points);
    }
}
