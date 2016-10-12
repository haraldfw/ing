package src.wilhelmsen.ing.alg.oving.eight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harald on 12.10.16.
 */
public class GraphGet {

    public static List<Node> getGraph(String filename) throws Exception {
        List<Node> nodes = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(filename));
        for(String s : lines) {
            String[] split = s.replaceAll("( )+", " ").trim().split(" ");

            String idFrom = split[0];
            String idTo = split[1];
            float weight = Float.parseFloat(split[2]);

            Node nodeFrom = getNode(idFrom, nodes);
            Node nodeTo = getNode(idTo, nodes);

            if(nodeFrom == null) {
                nodeFrom = new Node(idFrom);
                nodes.add(nodeFrom);
            }

            if(nodeTo == null) {
                nodeTo = new Node(idTo);
                nodes.add(nodeTo);
            }

            nodeFrom.addVertex(new Edge(weight, nodeFrom, nodeTo));
        }

        return nodes;
    }

    public static Node getNode(String id, List<Node> nodes) {
        for (Node n : nodes) {
            if(n.getId().equals(id)) {
                return n;
            }
        }
        return null;
    }
}
