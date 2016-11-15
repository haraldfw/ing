package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public abstract class Heuristic {

    protected final double goalX;
    protected final double goalY;

    public Heuristic(double goalX, double goalY) {
        this.goalX = goalX;
        this.goalY = goalY;
    }

    public abstract double estimate(StarNode from);
}
