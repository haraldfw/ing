package src.wilhelmsen.ing.alg.oving.oving12;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Harald on 07.11.2016.
 */
public final class FileHandler {

    private FileHandler() {
    }

    public static byte[] readContextFile(String fileName) throws Exception {
        InputStream is = FileHandler.class.getResourceAsStream(fileName);
        return IOUtils.readFully(is, is.available());
    }

    public static void writeFile(String filename, byte[] data) throws Exception {
        FileUtils.writeByteArrayToFile(new File(filename), data);
    }
}
