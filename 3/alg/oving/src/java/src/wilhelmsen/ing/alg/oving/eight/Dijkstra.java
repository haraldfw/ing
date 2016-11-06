package src.wilhelmsen.ing.alg.oving.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Harald on 12.10.16.
 */
public class Dijkstra {

    public static String getPaths(List<Node> nodes, Node start) {
        if (!nodes.contains(start)) {
            return null;
        }

        List<Node> queue = new ArrayList<>();

        queue.addAll(nodes);

        // set weight of start node to zero
        start.setCumulativeWeight(0);


        while (!queue.isEmpty()) {
            Node curr = queue.remove(0);
            System.out.println(curr.getId());

            for (Edge edge : curr.getEdges()) {
                float dist = curr.getCumulativeWeight() + edge.getWeight();
                Node endNode = edge.getEndNode();
                if (dist < endNode.getCumulativeWeight()) {
                    System.out.println(curr.getId() + " set as parent for node " + endNode.getId() + " with dist " + dist);
                    endNode.setParent(curr);
                    endNode.setCumulativeWeight(dist);
                }
            }
            queue.sort((n1, n2) -> (int) (n1.getCumulativeWeight() - n2.getCumulativeWeight()));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("n\t\tp\t\tdis\n");
        for (Node n : nodes) {
            String id = n.getId();
            Node parentNode = n.getParent();
            String parent = parentNode == null ? "nl" : parentNode.getId();
            String dist = String.valueOf(n.getCumulativeWeight());
            sb.append(id);
            sb.append("\t\t");
            sb.append(parent);
            sb.append("\t\t");
            sb.append(dist);
            sb.append("\n");
        }
        return sb.toString();
    }
}
