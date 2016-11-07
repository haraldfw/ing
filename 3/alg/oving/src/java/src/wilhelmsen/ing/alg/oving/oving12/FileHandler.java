package src.wilhelmsen.ing.alg.oving.oving12;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.util.BitSet;

/**
 * Created by Harald on 07.11.2016.
 */
class FileHandler {

    private FileHandler() {
    }

    public static byte[] readFile(String fileName) throws Exception {
        InputStream is = FileHandler.class.getResourceAsStream(fileName);
        return IOUtils.readFully(is, is.available());
    }

    public static void writeFile(String filename, byte[] data) throws Exception {
        FileUtils.writeByteArrayToFile(new File(filename), data);
    }

    public static byte[] bitSetToByteAr(BitSet bits) {
        byte[] bytes = new byte[(bits.length() + 7) / 8];
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length - i / 8 - 1] |= 1 << (i % 8);
            }
        }
        return bytes;
    }
}
