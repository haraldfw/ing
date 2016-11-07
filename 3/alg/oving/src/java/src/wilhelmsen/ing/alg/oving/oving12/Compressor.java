package src.wilhelmsen.ing.alg.oving.oving12;

import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        byte[] bytes = compressor.compress(FileHandler.readFile("/compression/oppg12.txt"));
        FileHandler.writeFile("\\D:\\dev\\compressed.d", bytes);
    }

    private byte[] compress(byte[] input) throws Exception {
        Map<Byte, Node> nodes = getFrequencies(input);
        byte[] charsAsByteAr = getBytes(new ArrayList<>(nodes.values()));

        for (Map.Entry<Byte, Node> entry : nodes.entrySet()) {
            System.out.println(
                    BitsUtil.byteToUnicode(entry.getValue().getByteValue()) +
                            " " + entry.getValue().getFreq());
        }
        System.out.println("\n");

        Node tree = generateTree(nodes);
        Map<Byte, MyBitSet> huffmanCoding = generateCoding(tree);

        for (Map.Entry<Byte, MyBitSet> entry : huffmanCoding.entrySet()) {
            System.out.println(
                    BitsUtil.byteToUnicode(entry.getKey()) +
                            " " + BitsUtil.bitSetToBinaryString(entry.getValue()));
        }

        return buildCompressedFile(charsAsByteAr, FileHandler.bitSetToByteAr(compress(huffmanCoding, input)));
    }

    private byte[] buildCompressedFile(byte[] freqs, byte[] compressedContents) {
        /* File contents, in bytes:
        freqs-size[8]\
        freqs[freq-size]\
        contents
         */
        byte[] contents = new byte[8 + freqs.length + compressedContents.length];
        byte[] freqsSizeAr = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(freqs.length).array();

        System.arraycopy(freqsSizeAr, 0, contents, 0, 8);
        System.arraycopy(freqs, 0, contents, 8, freqs.length);
        System.arraycopy(compressedContents, 0, contents, 8 + freqs.length, compressedContents.length);

        return contents;
    }

    private BitSet compress(Map<Byte, MyBitSet> mapping, byte[] input) {
        MyBitSet bits = new MyBitSet(0);
        int shift = 0;
        for (int i = 0; i < input.length; i++) {
            byte b = input[i];
            MyBitSet set = concatBitSets(bits, mapping.get(b));
            shift += set.getRealSize();
        }
        return bits;
    }

    private MyBitSet concatBitSets(MyBitSet bitSet1, MyBitSet bitSet2) {
        int shift = bitSet1.getRealSize();
        int i = bitSet2.nextSetBit(0);
        while (i > -1) {
            bitSet1.set(shift + i);
            i = bitSet2.nextSetBit(i + 1);
        }
        bitSet1.setRealSize(shift + bitSet2.getRealSize());
        return bitSet1;
    }

    private Map<Byte, MyBitSet> generateCoding(Node node) {
        return generateCoding(node, new MyBitSet(0), new HashMap<>(), 0);
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

    /**
     * Returns the bytes in the node-objects ordered by their frequency
     *
     * @param nodes List of nodes.
     * @return A byte arrays ordered by frequency from the node-list
     */
    private byte[] getBytes(List<Node> nodes) {
        byte[] c = new byte[nodes.size()];
        Collections.sort(nodes);
        for (int i = 0; i < nodes.size(); i++) {
            c[i] = nodes.get(i).getByteValue();
        }
        return c;
    }

    private Node mergeNodes(Node left, Node right) {
        return new Node(left.getFreq() + right.getFreq(), left, right);
    }
}
