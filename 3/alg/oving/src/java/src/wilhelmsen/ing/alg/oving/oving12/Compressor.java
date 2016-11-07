package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.*;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        BitSet bitSet = compressor.compress(FileHandler.readFile("/compression/lipsum.txt"));
        FileHandler.writeFile("\\D:\\dev\\compressed.d", FileHandler.bitSetToByteAr(bitSet));
    }

    private BitSet compress(byte[] input) throws Exception {
        Map<Byte, Node> nodes = getFrequencies(input);
        for (Map.Entry<Byte, Node> entry : nodes.entrySet()) {
            System.out.println(
                    BitsUtil.byteToUnicode(entry.getValue().getByteValue()) +
                            " " + entry.getValue().getFreq());
        }
        System.out.println("\n");
        Node tree = generateTree(nodes);
        Map<Byte, MyBitSet> coding = generateCoding(tree);
        for (Map.Entry<Byte, MyBitSet> entry : coding.entrySet()) {
            System.out.println(
                    BitsUtil.byteToUnicode(entry.getKey()) +
                            " " + BitsUtil.bitSetToBinaryString(entry.getValue()));
        }
        return new BitSet();
    }

    private Map<Byte, MyBitSet> generateCoding(Node node) {
        return generateCoding(node, new MyBitSet(0), new HashMap<>(), 0);
    }

    private Map<Byte, MyBitSet> generateCoding(Node node, MyBitSet code, Map<Byte,
            MyBitSet> codes, int codeIndex) {
        if (node.isLeaf()) {
            codes.put(node.getByteValue(), code);
        } else {
            int realsize = codeIndex + 1;

            MyBitSet clone = BitsUtil.cloneBitSet(code, realsize);
            generateCoding(node.getLeft(), clone, codes, codeIndex + 1);

            clone = BitsUtil.cloneBitSet(code, realsize);
            clone.set(codeIndex, true);
            generateCoding(node.getRight(), clone, codes, codeIndex + 1);
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

            Node merged = mergeNodes(nodeOne, nodeTwo);
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

    private Node mergeNodes(Node left, Node right) {
        return new Node(left.getFreq() + right.getFreq(), left, right);
    }
}
