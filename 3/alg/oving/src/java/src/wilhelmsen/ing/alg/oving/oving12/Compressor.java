package src.wilhelmsen.ing.alg.oving.oving12;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Map;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        String filename = "oppg12.txt";
        byte[] bytes = compressor.compress(FileHandler.readContextFile("/compression/" + filename));
        FileHandler.writeFile("\\D:\\dev\\" + filename + ".d", bytes);
    }


    private byte[] compress(byte[] input) throws Exception {
        HuffmanTree huffmanTree = new HuffmanTree(input);

        byte[][] freqTable = getFrequencyTable(huffmanTree.nodeMap);
        byte[] encodedData = encodeData(huffmanTree, input).toByteArray();
        return buildCompressedFile(freqTable, encodedData);
    }

    private byte[] buildCompressedFile(byte[][] freqTable, byte[] compressedContents) {
        int bytesUsedForFreqLength = Integer.BYTES;
        byte[] freqChars = freqTable[0];
        byte[] freqNums = freqTable[1];
        int freqTableLength = freqChars.length + freqNums.length;

        byte[] contents = new byte[bytesUsedForFreqLength + freqTableLength + compressedContents.length];
        System.out.println("Frequency length: " + freqTableLength);

        byte[] freqsSizeBytes = ByteBuffer.allocate(bytesUsedForFreqLength).putInt(freqChars.length).array();
        System.out.println("freqsSizeBytes: " + ByteBuffer.wrap(freqsSizeBytes).getInt());

        System.arraycopy(freqsSizeBytes, 0, contents, 0, bytesUsedForFreqLength);

        System.arraycopy(freqChars, 0,
                contents, bytesUsedForFreqLength, freqChars.length);

        System.arraycopy(freqNums, 0, contents, bytesUsedForFreqLength + freqChars.length, freqNums.length);

        System.arraycopy(compressedContents, 0,
                contents, bytesUsedForFreqLength + freqTableLength, compressedContents.length);

        return contents;
    }

    private BitSet encodeData(HuffmanTree mapping, byte[] input) {
        MyBitSet bits = new MyBitSet(0);
        for (byte b : input) {
            BitsUtil.concatBitSets(bits, mapping.getCode(b));
        }
        return bits;
    }

    private byte[][] getFrequencyTable(Map<Byte, Node> freqMap) {
        byte[] characters = new byte[freqMap.size()];
        byte[] freqs = new byte[freqMap.size() * Integer.BYTES];

        int i = 0;
        for (Map.Entry<Byte, Node> entry : freqMap.entrySet()) {
            byte key = entry.getKey();
            characters[i] = key;
            Node node = entry.getValue();
            byte[] intAsBytes = ByteBuffer.allocate(Integer.BYTES).putInt(node.getFreq()).array();
            System.arraycopy(intAsBytes, 0, freqs, i * Integer.BYTES, Integer.BYTES);
            i++;
        }
        return new byte[][]{characters, freqs};
    }
}
