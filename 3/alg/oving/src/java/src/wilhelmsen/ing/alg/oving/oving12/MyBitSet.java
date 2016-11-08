package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.BitSet;

/**
 * Created by Harald on 7.11.16.
 */
public class MyBitSet extends BitSet {
    private int realSize;

    public MyBitSet(int realsize) {
        super(realsize);
        this.realSize = realsize;
    }

    public int getRealSize() {
        return realSize;
    }

    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    public void pushBit(boolean val) {
        set(realSize, val);
        realSize += 1;
    }

    public void pushByte(byte value) {
        this.realSize = BitsUtil.concatBitSets(this, realSize, BitSet.valueOf(new byte[]{value}), 8);
        for (int i = realSize; i < realSize + 8; i++) {
            if ((value & (1 << i)) > 0) {
                set(i);
            }
        }
        realSize += 8;
    }
}
