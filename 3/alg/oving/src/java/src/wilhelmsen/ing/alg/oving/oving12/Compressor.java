package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.*;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        BitSet bitSet = compressor.compress(FileHandler.readFile("/test_data.txt"));
        compressor.generateCoding(FileHandler.readFile("/test_data.txt"));
        FileHandler.writeFile("\\D:\\dev\\compressed.d", FileHandler.bitSetToByteAr(bitSet));
    }

    private BitSet compress(byte[] input) {

        return new BitSet();
    }

    private Node generateCoding(byte[] input) {
        Map<Byte, Node> nodes = getFrequencies(input);
        Node tree = generateTree(nodes);
        return tree;
    }

    private Map<Byte, BitSet> generateCoding(Node tree) {
        return new HashMap<>();
    }

    private Node generateTree(Map<Byte, Node> nodes) {
        Queue<Node> queue = new PriorityQueue<>(nodes.values());
        while (!queue.isEmpty()) {
            Node nodeOne = queue.poll();
            if (queue.isEmpty()) {
                return nodeOne;
            }
            Node nodeTwo = queue.poll();

            Node merged = mergeNodes(nodeOne, nodeTwo);
            queue.add(merged);
            System.out.println(String.valueOf(nodeOne.getFreq()) + " " + String.valueOf(nodeTwo.getFreq()));
        }
        return null;
    }

    private Map<Byte, Node> getFrequencies(byte[] input) {
        Map<Byte, Node> nodes = new HashMap<>();
        for (byte b : input) {
            Node node = nodes.get(b);
            if (node == null) {
                node = new Node(b, 0);
                nodes.put(b, node);
            }
            node.incrementFreq();
        }
        return nodes;
    }

    private Node mergeNodes(Node left, Node right) {
        return new Node(left.getFreq() + right.getFreq(), left, right);
    }
}
