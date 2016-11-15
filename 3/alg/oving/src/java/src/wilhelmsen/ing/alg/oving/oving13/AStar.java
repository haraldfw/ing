package src.wilhelmsen.ing.alg.oving.oving13;

import src.wilhelmsen.ing.alg.oving.oving13.estimate.Heuristic;
import src.wilhelmsen.ing.alg.oving.oving13.graph.Connection;
import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;

import java.util.*;

/**
 * Created by Harald on 9.11.16.
 */
public class AStar {

    private Heuristic heuristic;

    public AStar(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public int generatePath(StarNode start, StarNode goal) {
        Queue<StarNode> open = new PriorityQueue<>();
        List<StarNode> closed = new ArrayList<>();

        start.setEstimatedTotalCost(heuristic.estimate(start));

        open.add(start);

        int nodesVisited = 0;

        while (!open.isEmpty()) {
            nodesVisited++;
            StarNode curr = open.poll();

            if (curr == goal) {
                break;
            }

            closed.add(curr);
            Connection[] connections = curr.getConnections();
            if(connections == null) {
                continue;
            }
            for (Connection connection : curr.getConnections()) {
                // new node to evaluate
                StarNode neighbor = connection.end;

                // skip if node already evaluated
                if (closed.contains(neighbor)) {
                    continue;
                }

                // the neighbor's cost so far
                double newCostSoFar = curr.getCostSoFar() + connection.weight;

                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                } else if (newCostSoFar >= neighbor.getCostSoFar()) {
                    continue;
                }
                // this path is the best so far. Set curr as new parent of neighbor
                neighbor.setParent(curr);
                neighbor.setCostSoFar(newCostSoFar);
                neighbor.setEstimatedTotalCost(newCostSoFar + heuristic.estimate(neighbor));
            }
        }

        return nodesVisited;
    }


}
