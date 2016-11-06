/**
 * 
 */
package src.wilhelmsen.oving;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Harald Floor Wilhelmsen
 *
 */
public class Problem_7_PrimeGenerator_ArrayMethod {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		int primesToGenerate = 200000;
		int j = 1;
		
		double[] primes = new double[primesToGenerate];
		
		System.out.println("Starting generation of " + primesToGenerate 
				+ " primes at time " + new Date() + "..." + "\nMethod: Array");
		
		double timeStarted = System.nanoTime();
		
		for(int i = 1; i < primes.length; i++) {
			while(true) {
				j += 2;
				if(isPrime(j, primes, i)) {
					primes[i] = j;
					//System.out.println(j);
					break;
				}
			}
		}
		
		System.out.println(primesToGenerate + " primes generated. Time used: " + (System.nanoTime() - timeStarted)/Math.pow(10, 9) + " seconds");
		System.out.println("Finished generation of " + primesToGenerate + " primes at time " + new Date());
		
		//for(int i = 0; i < primes.length; i++) {
		//	System.out.println(primes[i]);
		//}
		
		//printToFile(primes);
	}
	
	private static void printToFile(double[] primes) {
		System.out.println("Printing to file...");
		PrintWriter w = null;
		try {
			w = new PrintWriter("primes.txt", "UTF-8");
			for(int i = 0; i < primes.length; i++) {
				w.println(primes[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {w.close();} catch (Exception ex) {}
		}
		System.out.println("Done printing...");
	}
	
	private static boolean isPrime(double n, double[] primes, int loc) {
		double d = 0;
		
		for(int i = 0; d*d <= n && i < loc; i++) {
			d = primes[i];
			
			if(n%d == 0) return false;
		}
		return true;
	}
}