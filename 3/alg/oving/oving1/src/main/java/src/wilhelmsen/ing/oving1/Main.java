package src.wilhelmsen.ing.oving1;

/**
 * Created by Harald Floor Wilhelmsen on 22.08.2016.
 */
public class Main {

    public static void main(String[] args) {
        int[] stockDiff = new int[]{-1, +3, -9, +2, +2, -1, +2, -1, -5};
        measureTime(() -> stockBroker(stockDiff));
    }

    private static void measureTime(Runnable method) {
        long timeStarted = System.nanoTime();
        method.run();
        long timeEnded = System.nanoTime();
        System.out.println(method.getClass().getName() + " took in millis: " +
                (timeEnded - timeStarted)/1000);
    }

    public static int[] stockBroker(int[] stockDiff) {
        // {diff, buy-day, sell-day}
        int[] values = {-1, -1, -1};

        for (int i = 0; i < stockDiff.length - 1; i++) {
            int diffThisFar = 0;
            for (int j = i + 1; j < stockDiff.length; j++) {
                diffThisFar += stockDiff[j];
                if(diffThisFar > values[0]) {
                    values[0] = diffThisFar;
                    values[1] = i + 1;
                    values[2] = j + 1;
                }
            }
        }
        return values;
    }
}
