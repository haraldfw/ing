package src.wilhelmsen.ing.oving1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Harald Floor Wilhelmsen on 24.08.2016.
 */
public class MainTest {

    private int[] stockDiff = new int[]{-1, +3, -9, +2, +2, -1, +2, -1, -5};

    @Test
    public void testStock() throws Exception {
        int[] values = Main.stockBroker(stockDiff);
        Assert.assertEquals("Diff is correct", 5, values[0]);
        Assert.assertEquals("Buy-day is correct", 3, values[1]);
        Assert.assertEquals("Sell-day is correct", 7, values[2]);
    }
}
