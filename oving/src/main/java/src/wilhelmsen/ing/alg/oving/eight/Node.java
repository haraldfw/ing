package src.wilhelmsen.ing.alg.oving.eight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harald on 12.10.16.
 */
public class Node {
    private final String id;
    private final List<Vertex> vertices;

    public boolean visited = false;

    private float cumulativeWeight = Float.POSITIVE_INFINITY;

    private Node parent;

    public Node(String id) {
        this.id = id;
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public String getId() {
        return id;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public float getCumulativeWeight() {
        return cumulativeWeight;
    }

    public void setCumulativeWeight(float cumulativeWeight) {
        this.cumulativeWeight = cumulativeWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("\n");
            sb.append(id);
            sb.append(" ");
            sb.append(v.getEndNode().getId());
            sb.append(" ");
            sb.append(v.getWeight());
        }
        if(sb.toString().isEmpty())
            sb.append("\n");

        return sb.toString();
    }
}
