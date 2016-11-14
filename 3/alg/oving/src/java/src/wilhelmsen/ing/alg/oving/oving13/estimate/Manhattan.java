package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Manhattan extends Heuristic {

    public Manhattan(double goalX, double goalY) {
        super(goalX, goalY);
    }

    public double estimate(StarNode from) {
        return Math.abs(goalX - from.x + goalY - from.y);
    }
}
