package src.wilhelmsen.ing.alg.oving.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Harald on 9.11.16.
 */
public class FileUtils {
    public static InputStream getResourceStream(String path) {
        return FileUtils.class.getResourceAsStream(path);
    }

    public static BufferedReader getResourceReader(String path) {
        return new BufferedReader(new InputStreamReader(getResourceStream(path)));
    }
}
