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
        Map<Byte, Node> nodes = Huffman.getFrequencies(input);

        nodes.values().forEach(value -> System.out.println(
                BitsUtil.byteToUnicode(value.getByteValue()) + " " + value.getFreq()));
        System.out.println("\n");

        Node tree = Huffman.generateTree(nodes);
        Map<Byte, MyBitSet> huffmanCoding = Huffman.generateCoding(tree);

        for (Map.Entry<Byte, MyBitSet> entry : huffmanCoding.entrySet()) {
            System.out.println(
                    BitsUtil.byteToUnicode(entry.getKey()) +
                            " " + BitsUtil.bitSetToBinaryString(entry.getValue()));
        }

        byte[] charsAsByteAr = getStorableBytes(huffmanCoding);
        return buildCompressedFile(charsAsByteAr, FileHandler.bitSetToByteAr(compress(huffmanCoding, input)));
    }

    private byte[] buildCompressedFile(byte[] freqs, byte[] compressedContents) {
        /* File contents, in bytes:
        freqs-size[1]\
        freqs[freq-size]\
        contents
         */
        int bytesUsedForFreqLength = 1;
        byte[] contents = new byte[bytesUsedForFreqLength + freqs.length + compressedContents.length];
        System.out.println(freqs.length);
//        byte[] freqsSizeBytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(freqs.length).array();
        byte freqsSizeBytes = (byte) freqs.length;
        System.out.println(freqsSizeBytes);
        contents[0] = freqsSizeBytes;
//        System.arraycopy(freqsSizeBytes, 0,
//                contents, 0, bytesUsedForFreqLength);
        System.arraycopy(freqs, 0,
                contents, bytesUsedForFreqLength, freqs.length);
        System.arraycopy(compressedContents, 0,
                contents, bytesUsedForFreqLength + freqs.length, compressedContents.length);

        return contents;
    }

    private BitSet compress(Map<Byte, MyBitSet> mapping, byte[] input) {
        MyBitSet bits = new MyBitSet(0);
        for (int i = 0; i < input.length; i++) {
            byte b = input[i];
            MyBitSet set = BitsUtil.concatBitSets(bits, mapping.get(b));
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
            MyBitSet code =  entry.getValue();

            i++;
        }
        return bitSet;
    }

    private byte[] getStorableBytes(Map<Byte, MyBitSet> huffmanCoding) {
        byte[] bytes = new byte[huffmanCoding.size()];

        int i = 0;
        for (Byte key : huffmanCoding.keySet()) {
            bytes[i] = key;
            i++;
        }
        return bytes;
    }
}
