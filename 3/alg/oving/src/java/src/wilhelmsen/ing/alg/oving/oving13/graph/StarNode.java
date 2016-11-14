package src.wilhelmsen.ing.alg.oving.oving13.graph;

/**
 * Created by Harald on 9.11.16.
 */
public class StarNode implements Comparable<StarNode> {

    public final double x;
    public final double y;

    private double costSoFar = -1;
    private double estimatedTotalCost = -1;

    private StarNode parent;

    private Connection[] connections;

    public StarNode(double x, double y) {
        this.x = x;
        this.y = y;
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
        double diff = that.estimatedTotalCost - this.estimatedTotalCost;
        return diff < 0 ? -1 : (diff > 0 ? 1 : 0);
    }

    public Connection[] getConnections() {
        return connections;
    }

    public void setConnections(Connection[] connections) {
        this.connections = connections;
    }
}
