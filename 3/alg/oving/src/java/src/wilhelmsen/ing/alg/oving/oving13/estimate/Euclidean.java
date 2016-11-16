package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Euclidean extends Heuristic {

    public Euclidean(double goalX, double goalY) {
        super(goalX, goalY);
    }

    public Euclidean(StarNode node) {
        super(node);
    }

    @Override
    public double estimate(StarNode from) {
        double xDiff = goalLengde - from.breddeRad;
        double yDiff = goalBredde - from.lengdeRad;
        return Math.sqrt(xDiff * yDiff + yDiff * yDiff);
    }
}
