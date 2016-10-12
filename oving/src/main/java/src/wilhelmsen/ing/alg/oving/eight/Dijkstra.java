package src.wilhelmsen.ing.alg.oving.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Harald on 12.10.16.
 */
public class Dijkstra {

    public static String getPaths(List<Node> nodes, Node start) {
        if(!nodes.contains(start)) {
            return null;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> (int) (n1.getCumulativeWeight() - n2.getCumulativeWeight()));

        queue.addAll(nodes);

        // set weight of start node to zero
        start.setCumulativeWeight(0);

        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            curr.visited = true;

            curr.getVertices().forEach(vertex -> {
                float dist = curr.getCumulativeWeight() + vertex.getWeight();
                Node endNode = vertex.getEndNode();
                if (dist < endNode.getCumulativeWeight()) {
                    endNode.setParent(curr);
                    endNode.setCumulativeWeight(dist);
                }
            });
        }

        StringBuilder sb = new StringBuilder();
        sb.append("n\tp\t\tdis\n");
        for(Node n : nodes) {
            String id = n.getId();
            Node parentNode = n.getParent();
            String parent = parentNode == null ? "null" : parentNode.getId();
            String dist = String.valueOf(n.getCumulativeWeight());
            sb.append(id);
            sb.append("\t");
            sb.append(parent);
            sb.append("\t");
            sb.append(dist);
            sb.append("\n");
        }
        return sb.toString();
    }


}
