package src.wilhelmsen.ing.alg.oving.oving12;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        BitSet bitSet = compressor.compress(FileHandler.readFile("/test_data.txt"));
        FileHandler.writeFile("\\D:\\dev\\compressed.d", FileHandler.bitSetToByteAr(bitSet));
    }

    private BitSet compress(byte[] input) throws Exception {
        Map<Byte, Node> nodes = getFrequencies(input);
        for (Map.Entry<Byte, Node> entry : nodes.entrySet()) {
            System.out.println(byteToUnicode(entry.getValue().getValue()) + " " + entry.getValue().getFreq());
        }
        System.out.println("\n");
        Node tree = generateTree(nodes);
        Map<Byte, BitSet> coding = generateCoding(tree, new BitSet(), new HashMap<>(), 0);
        for(Map.Entry<Byte, BitSet> entry : coding.entrySet()) {
            System.out.println(byteToUnicode(entry.getKey()) + " " + bitSetToBinaryString(entry.getValue()));
        }
        return new BitSet();
    }

    private Map<Byte, BitSet> generateCoding(Node tree, BitSet code, Map<Byte, BitSet> codes, int codeIndex) {
        if(tree.isLeaf()) {
            codes.put(tree.getValue(), code);
        } else {
            BitSet clone = (BitSet) code.clone();
            clone.set(codeIndex, false);
            generateCoding(tree.getLeft(), clone, codes, codeIndex + 1);

            clone = (BitSet) code.clone();
            clone.set(codeIndex, true);
            generateCoding(tree.getRight(), clone, codes, codeIndex + 1);
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
//            System.out.println(String.valueOf(nodeOne.getFreq()) + " " + String.valueOf(nodeTwo.getFreq()));
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

    private String bitSetToBinaryString(BitSet set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < set.length(); i++) {
            sb.append(set.get(i) ? "1" : "0");
        }
        return sb.toString();
    }

    private String byteToUnicode(byte b) {
        try {
            return new String(new byte[]{b}, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Unsupported byte";
    }
}
