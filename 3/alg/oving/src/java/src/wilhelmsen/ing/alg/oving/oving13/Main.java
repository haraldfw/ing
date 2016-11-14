package src.wilhelmsen.ing.alg.oving.oving13;

import src.wilhelmsen.ing.alg.oving.oving13.estimate.Euclidean;

/**
 * Created by Harald on 9.11.16.
 */
public class Main {
    public static void main(String[] args) {
        new AStar(new Euclidean(1, 1));
    }
}
