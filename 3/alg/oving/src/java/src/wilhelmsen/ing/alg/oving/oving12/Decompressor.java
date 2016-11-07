package src.wilhelmsen.ing.alg.oving.oving12;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Harald on 07.11.2016.
 */
public class Decompressor {

    public static void main(String[] args) throws Exception {
        Decompressor decompressor = new Decompressor();
        String filename = "D:\\dev\\compressed.d";
        byte[] input = Files.readAllBytes(Paths.get(filename));
        System.out.println(new String(decompressor.decompress(input), "UTF-8"));
    }

    private byte[] decompress(byte[] input) {
        // -- splitting input --
        // freqsize
        int freqSize = input[0];
        System.out.println("Frequencies size: " + freqSize);
        int shift = 1;
        // freqs
        byte[] nodes = new byte[freqSize];
        System.arraycopy(input, shift, nodes, 0, freqSize);
        System.out.println("Sorted frequencies:");
        System.out.println(BitsUtil.byteArToUnicode(nodes));
        // compressed content
        shift += freqSize;
        byte[] remainingBytes = new byte[input.length - shift];
        System.arraycopy(input, shift, remainingBytes, 0, remainingBytes.length);
        System.out.println("Contents:");
        System.out.println(BitsUtil.byteArToUnicode(remainingBytes));

        return new byte[0];
    }

    private byte[] decode(){
        return null;
    }


}
