package src.wilhelmsen.ing.alg.oving.oving13;

import src.wilhelmsen.ing.alg.oving.oving12.FileHandler;
import src.wilhelmsen.ing.alg.oving.oving13.estimate.Haversine;
import src.wilhelmsen.ing.alg.oving.oving13.estimate.Manhattan;
import src.wilhelmsen.ing.alg.oving.oving13.graph.Connection;
import src.wilhelmsen.ing.alg.oving.oving13.graph.StarNode;
import src.wilhelmsen.ing.alg.oving.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harald on 9.11.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Map<Integer, StarNode> graph = main.buildGraph();

        StarNode start = graph.get(2146837);

        StarNode goal = graph.get(2341264);

        main.runAStar(start, goal);
        main.createMapHtml(start, goal, "D:\\Dev\\astar.html");

        graph.values().forEach(StarNode::reset);

        main.runDijkstra(start, goal);

        main.createMapHtml(start, goal, "D:\\Dev\\dijkstra.html");
    }

    private void runAStar(StarNode start, StarNode goal) {
        System.out.println("ASTAR: Starting...");
        AStar aStar = new AStar(new Haversine(goal.x, goal.y));
        int nodesVisited;
        long startTime = System.currentTimeMillis();
        nodesVisited = aStar.generatePath(start, goal);
        long endTime = System.currentTimeMillis();
        System.out.println("ASTAR: Done in " + (endTime - startTime) + "ms. Visited "
                + nodesVisited + " nodes. Total travel-time is " + goal.getCostSoFar());
    }

    private void runDijkstra(StarNode start, StarNode goal) {
        System.out.println("DJIKS: Starting...");
        int nodesVisited;
        long startTime = System.currentTimeMillis();
        nodesVisited = Dijkstra.generatePath(start, goal);
        long endTime = System.currentTimeMillis();
        System.out.println("DJIKS: Done in " + (endTime - startTime) + "ms. Visited "
                + nodesVisited + " nodes. Total travel-time is " + goal.getEstimatedTotalCost());
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
                connList.add(new Connection(nodeMap.get(end), time));
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

    private void createMapHtml(StarNode start, StarNode goal, String filename) throws Exception {
        String map_start = new String(FileHandler.readContextFile("/graph/maps_start.html"), "UTF-8");
        String map_end = new String(FileHandler.readContextFile("/graph/maps_end.html"), "UTF-8");

        List<String> coordinateStrings = new ArrayList<>();
        StarNode curr = goal;
        while (!start.equals(curr)) {
            coordinateStrings.add("{lat:" + String.valueOf(curr.x) + ", lng:" + String.valueOf(curr.y) + "}");
            curr = curr.getParent();
        }

        String html = map_start + String.join(",", coordinateStrings) + map_end;
        org.apache.commons.io.FileUtils.writeStringToFile(new File(filename), html, "UTF-8");
    }
}
