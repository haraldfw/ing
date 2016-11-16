package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public abstract class Heuristic {

    protected final double goalBredde;
    protected final double goalLengde;

    public Heuristic(double bredde, double lengde) {
        this.goalBredde = bredde;
        this.goalLengde = lengde;
    }

    public Heuristic(StarNode node) {
        this.goalBredde = node.breddeRad;
        this.goalLengde = node.lengdeRad;
    }

    public abstract double estimate(StarNode from);
}
