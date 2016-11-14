package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Euclidean2 extends Heuristic {

    public Euclidean2(double goalX, double goalY) {
        super(goalX, goalY);
    }

    /**
     * Returns the Euclidean distance to the power of 2.
     * @param from
     * @return
     */
    @Override
    public double estimate(StarNode from) {
        double xDiff = goalX - from.x;
        double yDiff = goalY - from.y;
        return xDiff * yDiff + yDiff * yDiff;
    }
}