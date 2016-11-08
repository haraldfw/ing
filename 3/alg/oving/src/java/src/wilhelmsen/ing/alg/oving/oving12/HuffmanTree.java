package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Harald on 7.11.16.
 */
public class HuffmanTree {

    public Map<Byte, MyBitSet> coding;
    public Node tree;

    /**
     * Creates a huffman coding tree from raw uncompressed data.
     *
     * @param input
     */
    public HuffmanTree(byte[] input) {
        Map<Byte, Node> nodeMap = getFrequencies(input);

        nodeMap.values().forEach(value -> System.out.println(
                BitsUtil.byteToUnicode(value.getByteValue()) + " " + value.getFreq()));
        System.out.println("\n");

        tree = generateTree(nodeMap);
        coding = generateCoding(tree, new MyBitSet(0), new HashMap<>(), 0);

        coding.entrySet().forEach(
                entry -> System.out.println(
                        BitsUtil.byteToUnicode(entry.getKey()) +
                                " " + BitsUtil.bitSetToBinaryString(entry.getValue())));
    }

    public HuffmanTree(Node tree) {
        this.tree = tree;
        coding = generateCoding(tree, new MyBitSet(0), new HashMap<>(), 0);
    }

    public MyBitSet getCode(byte character) {
        return coding.get(character);
    }

    private Map<Byte, MyBitSet> generateCoding(Node node, MyBitSet code, Map<Byte,
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

    private Node generateTree(Map<Byte, Node> nodes) {
        Queue<Node> queue = new PriorityQueue<>(nodes.values());
        while (!queue.isEmpty()) {
            Node nodeOne = queue.poll();
            if (queue.isEmpty()) {
                return nodeOne;
            }
            Node nodeTwo = queue.poll();

            Node merged = new Node(nodeOne, nodeTwo);
            queue.add(merged);
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

}
