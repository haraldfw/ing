package src.wilhelmsen.ing.alg.oving.oving12;

import java.io.UnsupportedEncodingException;

/**
 * Created by Harald on 7.11.16.
 */
public final class BitsUtil {

    private BitsUtil() {
    }

    public static MyBitSet cloneBitSet(MyBitSet toClone, int sizeOfNewSet) {
        int realSize = toClone.getRealSize();
        MyBitSet clone = new MyBitSet(sizeOfNewSet);
        for (int i = 0; i < realSize; i++) {
            if (toClone.get(i)) {
                clone.set(i);
            }
        }
        return clone;
    }

    public static String bitSetToBinaryString(MyBitSet set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < set.getRealSize(); i++) {
            sb.append(set.get(i) ? "1" : "0");
        }
        return sb.toString();
    }

    public static String byteToUnicode(byte b) {
        return byteArToUnicode(new byte[]{b});
    }

    public static String byteArToUnicode(byte[] bAr) {
        try {
            return new String(bAr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Unsupported byte";
    }
}
