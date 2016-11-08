package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.BitSet;

/**
 * Created by Harald on 7.11.16.
 */
public class MyBitSet extends BitSet {
    private int realSize;
    private int index = 0;

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

    public boolean readBit() {
        boolean bit = get(index);
        index++;
        return bit;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyBitSet)) {
            return false;
        }
        MyBitSet bs2 = (MyBitSet) obj;
        boolean realSizeSame = (bs2.getRealSize() == realSize);
        return super.equals(obj) && realSizeSame;
    }

    public boolean isRead() {
        return index >= realSize;
    }
}
