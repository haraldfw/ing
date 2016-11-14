package src.wilhelmsen.ing.alg.oving.oving13.estimate;

import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

/**
 * Created by Harald on 9.11.16.
 */
public class Haversine extends Heuristic {

    public static final double EARTH_RADIUS = 6371; // In kilometers

    public Haversine(double goalX, double goalY) {
        super(goalX, goalY);
    }

    @Override
    public double estimate(StarNode from) {
        double lat1 = from.x;
        double lat2 = goalX;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(goalY - from.y);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2),2);
        return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(a));
    }
}
