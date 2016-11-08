package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.BitSet;
import java.util.Map;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        byte[] bytes = compressor.compress(FileHandler.readContextFile("/compression/oppg12.txt"));
        FileHandler.writeFile("\\D:\\dev\\compressed.d", bytes);
    }


    private byte[] compress(byte[] input) throws Exception {
        HuffmanTree huffmanTree = new HuffmanTree(input);
//        byte[] charsAsByteAr = getStorableBytes(huffmanTree);
        byte[] charsAsByteAr = encodedNode(huffmanTree.tree).toByteArray();
        return buildCompressedFile(charsAsByteAr, FileHandler.bitSetToByteAr(compress(huffmanTree, input)));
    }

    private byte[] buildCompressedFile(byte[] huffmanTree, byte[] compressedContents) {
        /* File contents, in bytes:
        freqs-size[1]\
        freqs[freq-size]\
        contents
         */
        int bytesUsedForFreqLength = 1;
        byte[] contents = new byte[bytesUsedForFreqLength + huffmanTree.length + compressedContents.length];
        System.out.println(huffmanTree.length);
//        byte[] freqsSizeBytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(freqs.length).array();
        byte freqsSizeBytes = (byte) huffmanTree.length;
        System.out.println(freqsSizeBytes);
        contents[0] = freqsSizeBytes;
//        System.arraycopy(freqsSizeBytes, 0,
//                contents, 0, bytesUsedForFreqLength);
        System.arraycopy(huffmanTree, 0,
                contents, bytesUsedForFreqLength, huffmanTree.length);
        System.arraycopy(compressedContents, 0,
                contents, bytesUsedForFreqLength + huffmanTree.length, compressedContents.length);

        return contents;
    }

    private BitSet compress(HuffmanTree mapping, byte[] input) {
        MyBitSet bits = new MyBitSet(0);
        for (int i = 0; i < input.length; i++) {
            byte b = input[i];
            MyBitSet set = BitsUtil.concatBitSets(bits, mapping.getCode(b));
        }
        return bits;
    }

    /**
     * Returns the bytes in the node-objects ordered by their frequency
     *
     * @return A byte arrays ordered by frequency from the node-list
     */
    private MyBitSet huffmanCodingToBitSet(Map<Byte, MyBitSet> huffmanCoding) {
        int largestSize = 0;
        for (MyBitSet set : huffmanCoding.values()) {
            int realsize = set.getRealSize();
            if (realsize > largestSize) {
                largestSize = realsize;
            }
        }
        System.out.println("Largest size:" + largestSize);

        MyBitSet bitSet = new MyBitSet(huffmanCoding.size() * (1 + largestSize));

        int i = 0;
        for (Map.Entry<Byte, MyBitSet> entry : huffmanCoding.entrySet()) {
            BitSet key = BitSet.valueOf(new byte[]{entry.getKey()});
            MyBitSet code = entry.getValue();

            i++;
        }
        return bitSet;
    }

    private byte[] getStorableBytes(HuffmanTree huffmanCoding) {
        byte[] bytes = new byte[huffmanCoding.coding.size()];

        int i = 0;
        for (Byte key : huffmanCoding.coding.keySet()) {
            bytes[i] = key;
            i++;
        }
        return bytes;
    }

    /**
     * Encode Huffman Node to BitSet
     * if leaf node pushes 1 + literal byte
     * otherwise 0
     *
     * @return bs BitSet with encoded Node
     */
    public BitSet encodedNode(Node root) {
        MyBitSet bs = new MyBitSet(0);
        encodedNode(root, bs);
        return bs;
    }

    /**
     * recursive helper method
     */
    private void encodedNode(Node node, MyBitSet bs) {
        if (node.isLeaf()) {
            bs.pushBit(true);
            bs.pushByte(node.getByteValue());
        } else {
            bs.pushBit(false);
            encodedNode(node.getLeft(), bs);
            encodedNode(node.getRight(), bs);
        }
    }
}
