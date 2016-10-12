package src.wilhelmsen.ing.alg.oving.five;

import java.util.Random;

/**
 * Created by Harald on 21.9.16.
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = new int[10000000];
        Random r = new Random(1337);
        for(int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(Integer.MAX_VALUE);
        }

        for(int i : nums) {
            System.out.println(i + ": " +getHash(i));
        }
    }

    private static int getHash(int c) {
        int result = 17;
        int magicConstant = 31;
        return magicConstant * result + c;
    }
}
