package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Euclidean extends Heuristic {

    public Euclidean(double goalX, double goalY) {
        super(goalX, goalY);
    }

    @Override
    public double estimate(StarNode from) {
        double xDiff = goalX - from.x;
        double yDiff = goalY - from.y;
        return Math.sqrt(xDiff * yDiff + yDiff * yDiff);
    }
}
