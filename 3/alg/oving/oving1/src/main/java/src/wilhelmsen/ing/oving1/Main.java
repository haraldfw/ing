package src.wilhelmsen.ing.oving1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Harald Floor Wilhelmsen on 22.08.2016.
 */
public class Main {

    public static void main(String[] args) {
//        int[] stockDiff = new int[]{-1, 3, -9, 2, 2, -1, 2, -1, -5};
        List<Integer> stockDiffList = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            stockDiffList.add(r.nextInt(20) - 10);
        }
        measureTime(() -> stockBroker(stockDiffList.toArray(new Integer[stockDiffList.size()])));
    }

    private static void measureTime(Runnable method) {
        long timeStarted = System.nanoTime();
        method.run();
        long timeEnded = System.nanoTime();
        System.out.println(method.getClass().getSimpleName() + " took in millis: " +
                ((double) (timeEnded - timeStarted) / 1000000));
    }

    public static int[] stockBroker(Integer[] stockDiff) { // n = len(stockDiff)
        // {diff, buy-day, sell-day}
        int[] values = {-1, -1, -1};
        for (int i = 0; i < stockDiff.length - 1; i++) { // n - 1
            int profit = 0;
            for (int j = i + 1; j < stockDiff.length; j++) { // (n - 1) * (n - 1)
                profit += stockDiff[j];
                if (profit > values[0]) {
                    values[0] = profit; // diff
                    values[1] = i + 1; // buy
                    values[2] = j + 1; // sell
                }
            }
        }
        return values;
    }
}
