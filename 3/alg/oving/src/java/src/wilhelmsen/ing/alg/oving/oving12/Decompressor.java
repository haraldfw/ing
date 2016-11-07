package src.wilhelmsen.ing.alg.oving.oving12;

/**
 * Created by Harald on 07.11.2016.
 */
public class Decompressor {

    public static void main(String[] args) throws Exception {
        Decompressor decompressor = new Decompressor();
        String filename = "\\D:\\dev\\compressed.d";
        byte[] input = FileHandler.readFile(filename);
        System.out.println(new String(decompressor.decompress(input), "UTF-8"));
    }

    private byte[] decompress(byte[] input) {
        return new byte[0];
    }
}
