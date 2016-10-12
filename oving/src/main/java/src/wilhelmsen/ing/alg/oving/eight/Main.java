package src.wilhelmsen.ing.alg.oving.eight;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Harald on 12.10.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Node> nodes = GraphGet.getGraph("src/main/resources/nodes.txt");

        System.out.println(Arrays.toString(nodes.toArray()));

        System.out.println(Dijkstra.getPaths(nodes, GraphGet.getNode("1", nodes)));
    }
}
