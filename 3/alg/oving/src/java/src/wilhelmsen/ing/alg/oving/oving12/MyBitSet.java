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
}
