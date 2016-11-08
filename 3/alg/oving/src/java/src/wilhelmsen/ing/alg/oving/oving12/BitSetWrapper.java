package src.wilhelmsen.ing.alg.oving.oving12;

import java.util.BitSet;

/**
 * Created by Harald on 08.11.2016.
 */
public class BitSetWrapper {
    public BitSet bitSet;

    public BitSetWrapper(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    public BitSet consume(int endIndex) {
        BitSet consumed = bitSet.get(0, endIndex);
        bitSet = bitSet.get(endIndex, bitSet.length());
        return consumed;
    }
}
