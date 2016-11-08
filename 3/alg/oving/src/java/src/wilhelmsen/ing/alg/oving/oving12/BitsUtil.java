package src.wilhelmsen.ing.alg.oving.oving12;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;

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

    public static String byteArToUnicode(byte[] byteArray) {
        try {
            return new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Unsupported input";
    }

    public static MyBitSet concatBitSets(MyBitSet bitSet1, MyBitSet bitSet2) {
        int shift = bitSet1.getRealSize();
        int i = bitSet2.nextSetBit(0);
        while (i > -1) {
            bitSet1.set(shift + i);
            i = bitSet2.nextSetBit(i + 1);
        }
        bitSet1.setRealSize(shift + bitSet2.getRealSize());
        return bitSet1;
    }

    public static int concatBitSets(BitSet bitSet1, int realsize1, BitSet bitSet2, int realsize2) {
        int i = bitSet2.nextSetBit(0);
        while (i > -1) {
            bitSet1.set(realsize1 + i);
            i = bitSet2.nextSetBit(i + 1);
        }
        return realsize1 + realsize2;
    }
}
