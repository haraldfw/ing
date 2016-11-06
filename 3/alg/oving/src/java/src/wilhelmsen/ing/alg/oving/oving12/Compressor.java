package src.wilhelmsen.ing.alg.oving.oving12;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Harald on 06.11.2016.
 */
public class Compressor {
    public static void main(String[] args) throws Exception {
        Compressor compressor = new Compressor();
        System.out.println(new String(compressor.readFile("/test_data.txt")));
        compressor.writeFile("\\D:\\dev\\compressed.d", compressor.compress(compressor.readFile("/test_data.txt")));
    }

    private byte[] compress(byte[] input) {
        return "test_test".getBytes();
    }

    private byte[] decompress(byte[] input) {
        return new byte[0];
    }

    private byte[] readFile(String fileName) throws Exception {
        InputStream is = getClass().getResourceAsStream(fileName);
        return IOUtils.readFully(is, is.available());
    }

    private void writeFile(String filename, byte[] data) throws Exception {
        FileUtils.writeByteArrayToFile(new File(filename), data);
    }
}
