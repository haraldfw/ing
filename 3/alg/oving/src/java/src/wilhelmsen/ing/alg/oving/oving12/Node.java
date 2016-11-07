package src.wilhelmsen.ing.alg.oving.oving12;

/**
 * Created by Harald on 07.11.2016.
 */
public class Node implements Comparable<Node> {

    private byte byteValue;
    private int freq;
    private Node left;
    private Node right;

    public Node(byte byteValue, int freq) {
        this.byteValue = byteValue;
        this.freq = freq;
    }

    public Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node that) {
        return this.freq - that.freq;
    }

    public void incrementFreq() {
        freq++;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
