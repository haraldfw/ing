package src.wilhelmsen.ing.alg.oving.oving12;

import org.apache.commons.lang.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Harald on 07.11.2016.
 */
public class Decompressor {

    public static void main(String[] args) throws Exception {
        Decompressor decompressor = new Decompressor();
        String baseFilename = "D:\\dev\\lipsum.txt";
        byte[] input = Files.readAllBytes(Paths.get(baseFilename + ".d"));
        byte[] decompressed = decompressor.decompress(input);
        FileHandler.writeFile(baseFilename, decompressed);

    }

    private byte[] decompress(byte[] input) {
        // -- splitting input --
        // freqsize
        byte[] freqsSizeBytes = new byte[4];
        System.arraycopy(input, 0, freqsSizeBytes, 0, 4);

        int freqSize = ByteBuffer.wrap(freqsSizeBytes).getInt();
        System.out.println("Frequencies size: " + freqSize);
        int shift = Integer.BYTES;

        // freqs
        byte[] characters = new byte[freqSize];
        System.arraycopy(input, shift, characters, 0, freqSize);

        shift += freqSize;
        int[] freqs = new int[freqSize];
        for (int i = 0; i < freqs.length; i++) {
            int index = shift + i * Integer.BYTES;
            byte[] freqBytes = new byte[Integer.BYTES];
            System.arraycopy(input, index, freqBytes, 0, Integer.BYTES);
            int freqInt = ByteBuffer.wrap(freqBytes).getInt();
            freqs[i] = freqInt;
        }

        System.out.println("Frequency chars:");
        System.out.println(BitsUtil.byteArToUnicode(characters));
        System.out.println("Frequencies numbers:");
        System.out.println(Arrays.toString(freqs));


        // compressed content
        shift += freqSize * Integer.BYTES;
        byte[] remainingBytes = new byte[input.length - shift];
        System.arraycopy(input, shift, remainingBytes, 0, remainingBytes.length);

        HuffmanTree huffmanTree = new HuffmanTree(getNodes(characters, freqs));

        MyBitSet remainingBits = BitsUtil.cloneBitSet(BitSet.valueOf(remainingBytes), remainingBytes.length * 8);

        List<Byte> decodedData = new ArrayList<>();
        while (!remainingBits.isRead()) {
            byte b = huffmanTree.tree.getCharacter(remainingBits);
            decodedData.add(b);
        }

        Byte[] bytes = decodedData.toArray(new Byte[decodedData.size()]);
        return ArrayUtils.toPrimitive(bytes);
    }

    private Map<Byte, Node> getNodes(byte[] characters, int[] freqs) {
        Map<Byte, Node> nodes = new HashMap<>();
        for (int i = 0; i < characters.length; i++) {
            byte character = characters[i];
            Node node = new Node(character, freqs[i]);
            nodes.put(character, node);
        }
        return nodes;
    }

}
