package src.wilhelmsen.ing.oving1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harald Floor Wilhelmsen on 22.08.2016.
 */
public class Main {

    public static void main(String[] args) {
        measureTime(Main::stockBroker);
    }

    private static void measureTime(Runnable method) {
        long timeStarted = System.nanoTime();
        method.run();
        long timeEnded = System.nanoTime();
        System.out.println(method.getClass().getName() + " took in millis: " +
                (timeEnded - timeStarted)/1000);


        System.out.println("Best buy day: " + String.valueOf(buy + 1));
        System.out.println("Best sell day: " + String.valueOf(sell + 1));
        System.out.println("Diff: " + bestDiff);
    }

    private static int buy = -1;
    private static int sell = -1;
    private static int bestDiff = -1;

    private static void stockBroker() {
        int[] stockDiff = new int[]{-1, +3, -9, +2, +2, -1, +2, -1, -5};

        for (int i = 0; i < stockDiff.length - 1; i++) {
            int buyVal = stockDiff[i];
            int diffThisFar = 0;
            for (int j = i + 1; j < stockDiff.length; j++) {
                diffThisFar += stockDiff[j];
                int diff = diffThisFar - buyVal;
                if(diff > bestDiff) {
                    bestDiff = diff;
                    buy = i;
                    sell = j;
                }
            }
        }
    }
}
