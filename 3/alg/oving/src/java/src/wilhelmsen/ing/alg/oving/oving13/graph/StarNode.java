package src.wilhelmsen.ing.alg.oving.oving13.graph;

/**
 * Created by Harald on 9.11.16.
 */
public class StarNode implements Comparable<StarNode> {

    private static final double INIT_VAL = Double.POSITIVE_INFINITY;

    public final double breddeRad;
    public final double lengdeRad;
    public final double cosBredde;

    public final double bredde;
    public final double lengde;

    private double costSoFar = INIT_VAL;
    private double estimatedTotalCost = INIT_VAL;

    private StarNode parent;

    private Connection[] connections;

    public StarNode(double bredde, double lengde) {
        this.bredde = bredde;
        this.lengde = lengde;
        this.breddeRad = Math.toRadians(bredde);
        this.lengdeRad = Math.toRadians(lengde);
        this.cosBredde = Math.cos(bredde);
    }

    public StarNode getParent() {
        return parent;
    }

    public void setParent(StarNode parent) {
        this.parent = parent;
    }

    public double getCostSoFar() {
        return costSoFar;
    }

    public void setCostSoFar(double costSoFar) {
        this.costSoFar = costSoFar;
    }

    public double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    @Override
    public int compareTo(StarNode that) {
        double diff = estimatedTotalCost - that.estimatedTotalCost;
        return diff < 0 ? -1 : (diff > 0 ? 1 : 0);
    }

    public Connection[] getConnections() {
        return connections;
    }

    public void setConnections(Connection[] connections) {
        this.connections = connections;
    }

    public void reset() {
        costSoFar = INIT_VAL;
        estimatedTotalCost = INIT_VAL;
        parent = null;
    }
}
