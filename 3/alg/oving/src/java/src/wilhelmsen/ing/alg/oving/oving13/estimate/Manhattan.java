package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Manhattan extends Heuristic {

    public Manhattan(double goalX, double goalY) {
        super(goalX, goalY);
    }

    public Manhattan(StarNode node) {
        super(node.bredde, node.lengde);
    }

    public double estimate(StarNode from) {
        return Math.abs((goalBredde - from.breddeRad) + (goalLengde - from.lengdeRad));
    }
}
