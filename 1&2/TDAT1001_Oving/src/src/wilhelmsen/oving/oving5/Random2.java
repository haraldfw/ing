package src.wilhelmsen.oving.oving5;

import java.util.Random;

/**
 * Created by Harald Wilhelmsen on 9/19/2014.
 */
public class Random2 {

	private Random rng;

	public Random2(long seed) {
		rng = new Random(seed);
	}

	public Random2() {
		rng = new Random();
	}

	/**
	 *
	 * @param rangeStart	Inclusive range start
	 * @param rangeEnd	Inclusive range end
	 * @return	Random number on range
	 */
	public int nextInt(int rangeStart, int rangeEnd) {
		if(rangeEnd <= rangeStart) throw new IllegalArgumentException("rangeEnd must be bigger than rangeStart");
		return rng.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
	}

	public double nextDouble() {
		return rng.nextDouble();
	}
}
