package src.wilhelmsen.ing.alg.oving.oving13;

import src.wilhelmsen.ing.alg.oving.oving13.graph.Connection;
import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Harald on 12.10.16.
 */
public class Dijkstra {

    public static int generatePath(StarNode start, StarNode goal) {
        Queue<StarNode> queue = new PriorityQueue<>();

        queue.add(start);
        // set weight of start node to zero
        start.setEstimatedTotalCost(0);

        int nodesVisited = 0;

        while (!queue.isEmpty()) {
            nodesVisited++;
            StarNode curr = queue.poll();

            if (curr == goal) {
                break;
            }

            Connection[] connections = curr.getConnections();
            if (connections == null) {
                continue;
            }

            for (Connection connection : curr.getConnections()) {
                StarNode endNode = connection.end;

                double dist = curr.getEstimatedTotalCost() + connection.weight;

                if (dist < endNode.getEstimatedTotalCost()) {
                    endNode.setParent(curr);
                    endNode.setEstimatedTotalCost(dist);
                    if(queue.contains(endNode)) {
                        queue.remove(endNode);
                    }
                    queue.add(endNode);
                }
            }
        }
        return nodesVisited;
    }
}
