package src.wilhelmsen.ing.alg.oving.eight;

/**
 * Created by Harald on 12.10.16.
 */
public class Vertex {
    private final float weight;
    private final Node endNode;

    public Vertex(float weight, Node endNode) {
        this.weight = weight;
        this.endNode = endNode;
    }

    public float getWeight() {
        return weight;
    }

    public Node getEndNode() {
        return endNode;
    }
}
