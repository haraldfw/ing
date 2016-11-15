package src.wilhelmsen.ing.alg.oving.oving13.graph;

/**
 * Created by Harald on 9.11.16.
 */
public class Connection {

    public StarNode end;
    public double weight;

    public Connection(StarNode end, double weight) {
        this.end = end;
        this.weight = weight;
    }
}