package src.wilhelmsen.oving.oving6;

import java.util.Random;

/**
 * Created by Harald Wilhelmsen on 9/29/2014.
 */
public class Oppg1 {

	public static void main(String[] args) {
		Random rng = new Random();

		int amount = 100;
		int range = 10;

		int[] picks = new int[range];

		for(int i = 0; i < amount; i++) picks[rng.nextInt(range)]++;
		for(int i = 0; i < range; i++) {
			System.out.print("#" + i + ": " + picks[i] + "\t");
			for(int j = 0; j < picks[i]; j++) System.out.print("*");
			System.out.println();
		}
	}
}
