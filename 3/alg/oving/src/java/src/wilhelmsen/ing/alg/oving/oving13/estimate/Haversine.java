package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Haversine extends Heuristic {

    private final StarNode goal;

    public Haversine(StarNode goal) {
        super(goal);
        this.goal = goal;
    }

    @Override
    public double estimate(StarNode from) {
        double sin_bredde = Math.sin((goal.breddeRad - from.breddeRad) / 2.0);
        double sin_lengde = Math.sin((goal.lengdeRad - from.lengdeRad) / 2.0);
        return (int) (41701090.90909090909090909091 * Math.asin(Math.sqrt(
                sin_bredde * sin_bredde + goal.cosBredde * from.cosBredde * sin_lengde * sin_lengde)));
    }
}
