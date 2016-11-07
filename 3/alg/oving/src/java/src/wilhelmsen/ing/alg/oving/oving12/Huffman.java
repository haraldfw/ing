package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Harald on 7.11.16.
 */
public class Huffman {
    public Huffman() {
    }

    public static Map<Byte, MyBitSet> generateCoding(Node node) {
        return generateCoding(node, new MyBitSet(0), new HashMap<>(), 0);
    }

    private static Map<Byte, MyBitSet> generateCoding(Node node, MyBitSet code, Map<Byte,
            MyBitSet> codes, int codeIndex) {
        if (node.isLeaf()) {
            codes.put(node.getByteValue(), code);
        } else {
            int newIndexAndSize = codeIndex + 1;

            MyBitSet clone = BitsUtil.cloneBitSet(code, newIndexAndSize);
            generateCoding(node.getLeft(), clone, codes, newIndexAndSize);

            clone = BitsUtil.cloneBitSet(code, newIndexAndSize);
            clone.set(codeIndex, true);
            generateCoding(node.getRight(), clone, codes, newIndexAndSize);
        }
        return codes;
    }

    public static Node generateTree(Map<Byte, Node> nodes) {
        Queue<Node> queue = new PriorityQueue<>(nodes.values());
        while (!queue.isEmpty()) {
            Node nodeOne = queue.poll();
            if (queue.isEmpty()) {
                return nodeOne;
            }
            Node nodeTwo = queue.poll();

            Node merged = mergeNodes(nodeOne, nodeTwo);
            queue.add(merged);
        }
        return null;
    }

    public static Map<Byte, Node> getFrequencies(byte[] input) {
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

    private static Node mergeNodes(Node left, Node right) {
        return new Node(left.getFreq() + right.getFreq(), left, right);
    }
}
