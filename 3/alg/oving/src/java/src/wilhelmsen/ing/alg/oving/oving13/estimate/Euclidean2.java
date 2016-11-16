package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Euclidean2 extends Heuristic {

    public Euclidean2(double goalX, double goalY) {
        super(goalX, goalY);
    }

    public Euclidean2(StarNode node) {
        super(node);
    }

    /**
     * Returns the Euclidean distance to the power of 2.
     * @param from
     * @return
     */
    @Override
    public double estimate(StarNode from) {
        double xDiff = goalLengde - from.breddeRad;
        double yDiff = goalBredde - from.lengdeRad;
        return xDiff * yDiff + yDiff * yDiff;
    }
}