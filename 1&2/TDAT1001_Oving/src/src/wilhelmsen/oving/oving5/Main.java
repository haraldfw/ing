package src.wilhelmsen.oving.oving5;

/**
 * Created by Harald Wilhelmsen on 9/19/2014.
 */
public class Main {

	public static void main(String[] args) {
		Fraction.testAndPrint();
		System.out.print("\n\n");

		System.out.println("Testing random2");
		Random2 r = new Random2();
		/*	Tested nextDouble Integer.MAX_VALUE amount of times. Results:
			Biggest d: 0.9999999995730402
			Smallest d: 1.8589935146806624E-9 */
		double b = 0;
		double s = 1;
		for(int i = 0; i < 1000; i++) {
			Double d = r.nextDouble();
			if(d > b) b = d;
			else if(d < s) s = d;
		}

		int rs = 10;
		int re = 25;
		int[] results = new int[re - rs + 1];
		for(int i = 0; i < 10000; i++) results[r.nextInt(rs, re) - rs]++;
		System.out.println("Results: ");
		for(int i = 0; i < results.length; i++) {
			System.out.println(rs + i + ": " + results[i]);
		}
	}
}
