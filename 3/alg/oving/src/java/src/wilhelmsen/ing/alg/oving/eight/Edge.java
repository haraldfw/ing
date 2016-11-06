package src.wilhelmsen.ing.alg.oving.eight;

/**
 * Created by Harald on 12.10.16.
 */
public class Edge {
    private final float weight;
    private final Node startNode;
    private final Node endNode;

    public Edge(float weight, Node startNode, Node endNode) {
        this.weight = weight;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public float getWeight() {
        return weight;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Node getStartNode() {
        return startNode;
    }
}
