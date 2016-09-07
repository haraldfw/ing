package src.wilhelmsen.ing.alg.oving.three;

import java.util.Random;

/**
 * Created by Harald on 7.9.16.
 */
public class BetterQuickSort {
    public static void main(String[] args) {

        Random random = new Random(1337);

        double betterUsed = 0;
        double badUsed = 0;
        int runs = 1000;
        for (int i = 0; i < runs; i++) {
            int tableSize = 50000;
            int bound = 10;

            int low = 0;
            int high = tableSize - 1;

            long timeStart = System.nanoTime();
            betterQuickSort(getArr(new Random(1337), tableSize, bound), low, high);
            betterUsed += System.nanoTime() - timeStart;

            timeStart = System.nanoTime();
            badQuickSort(getArr(new Random(1337), tableSize, bound), low, high);
            badUsed += System.nanoTime() - timeStart;
        }

        betterUsed /= 1000000.0; // convert to milliseconds
        badUsed /= 1000000.0; // convert to milliseconds
        System.out.println("BetterQuickSort used " + betterUsed + " milliseconds when running algo " + runs + " times");
        System.out.println("This is an avg of " + (betterUsed / (double) runs) + " ms per run");
        System.out.println("\n BadQuickSort used " + badUsed + " milliseconds when running algo " + runs + " times");
        System.out.println("This is an avg of " + (badUsed / (double) runs) + " ms per run");
    }

    private static int[] getArr(Random random, int tableSize, int bound) {
        int[] arr = new int[tableSize];
        for (int j = 0; j < tableSize; j++) {
            arr[j] = random.nextInt(bound);
        }

        return arr;
    }

    private static void betterQuickSort(int[] t, int v, int h) {
        if (t == null || t.length == 0)
            return;

        if (v >= h)
            return;

        if (v > 0 && h < t.length - 1 && t[v - 1] == t[h + 1]) return;

        // pick the pivot
        int middle = v + (h - v) / 2;
        int pivot = t[middle];

        // make left < pivot and right > pivot
        int i = v, j = h;
        while (i <= j) {
            while (t[i] < pivot) {
                i++;
            }

            while (t[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = t[i];
                t[i] = t[j];
                t[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (v < j)
            betterQuickSort(t, v, j);

        if (h > i)
            betterQuickSort(t, i, h);
    }

    private static void badQuickSort(int[] t, int v, int h) {
        if (t == null || t.length == 0)
            return;

        if (v >= h)
            return;

        // pick the pivot
        int middle = v + (h - v) / 2;
        int pivot = t[middle];

        // make left < pivot and right > pivot
        int i = v, j = h;
        while (i <= j) {
            while (t[i] < pivot) {
                i++;
            }

            while (t[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = t[i];
                t[i] = t[j];
                t[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (v < j)
            badQuickSort(t, v, j);

        if (h > i)
            badQuickSort(t, i, h);
    }
}
