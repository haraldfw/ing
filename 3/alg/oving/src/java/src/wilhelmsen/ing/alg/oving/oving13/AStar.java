package src.wilhelmsen.ing.alg.oving.oving13;

import src.wilhelmsen.ing.alg.oving.oving13.estimate.Heuristic;
import src.wilhelmsen.ing.alg.oving.oving13.graph.Connection;
import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;
import src.wilhelmsen.ing.alg.oving.util.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harald on 9.11.16.
 */
public class AStar {

    private final Map<Integer, StarNode> graph;
    private Heuristic heuristic;

    public AStar(Heuristic heuristic) {
        graph = buildGraph();
        this.heuristic = heuristic;
    }

    private void generatePath(int startId, int endId) {
        generatePath(graph.get(startId), graph.get(endId));
    }

    private void generatePath(StarNode start, StarNode end) {

    }

    private Map<Integer, StarNode> buildGraph() {
        Map<Integer, StarNode> nodeMap = getNodes("/graph/noder.txt");
        mergeNodes(nodeMap, "/graph/kanter.txt");

        return nodeMap;
    }

    private void mergeNodes(Map<Integer, StarNode> nodeMap, String edgesFilePath) {
        Map<Integer, List<Connection>> connMap = createConnections(nodeMap, edgesFilePath);

        // insert connections into each StarNode
        for (Map.Entry<Integer, List<Connection>> entry : connMap.entrySet()) {
            int key = entry.getKey();
            List<Connection> connList = entry.getValue();
            Connection[] connections = connList.toArray(new Connection[connList.size()]);
            nodeMap.get(key).setConnections(connections);
        }
    }

    private Map<Integer, List<Connection>> createConnections(Map<Integer, StarNode> nodeMap, String edgesFilePath) {
        Map<Integer, List<Connection>> connMap = new HashMap<>();
        BufferedReader br = FileUtils.getResourceReader(edgesFilePath);
        int connectionsCreated = 0;
        try {
            // count-line
            System.out.println("CONNS: Creating " + br.readLine().trim() + " connections.");
            for (String line; (line = br.readLine()) != null; ) {
                String[] elems = splitLine(line);

                // start, end, time, length, speedlimit
                int start = Integer.valueOf(elems[0]);
                int end = Integer.valueOf(elems[1]);
                int time = Integer.valueOf(elems[2]);

                List<Connection> connList = connMap.get(start);
                if (connList == null) {
                    connList = new ArrayList<>();
                    connMap.put(start, connList);
                }
                connList.add(new Connection(nodeMap.get(start), nodeMap.get(end), time));
                connectionsCreated++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CONNS: Done. Created " + connectionsCreated + " connections.");
        return connMap;
    }

    private Map<Integer, StarNode> getNodes(String path) {
        Map<Integer, StarNode> nodeMap = new HashMap<>();

        BufferedReader br = FileUtils.getResourceReader(path);
        try {
            // count-line
            System.out.println("NODES: Creating " + br.readLine().trim() + " nodes...");
            for (String line; (line = br.readLine()) != null; ) {
                String[] elems = splitLine(line);

                // id, x, y
                int id = Integer.valueOf(elems[0]);
                double x = Double.valueOf(elems[1]);
                double y = Double.valueOf(elems[2]);

                StarNode node = new StarNode(x, y);
                nodeMap.put(id, node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("NODES: Done. Created " + nodeMap.size() + " nodes.");

        return nodeMap;
    }

    private String[] splitLine(String line) {
        return line.trim().split("[\t( )]+");
    }
}
