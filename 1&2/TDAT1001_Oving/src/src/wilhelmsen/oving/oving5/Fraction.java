package src.wilhelmsen.oving.oving5;

/**
 * Created by Harald Wilhelmsen on 9/19/2014.
 */
public class Fraction {

	private double numerator;
	private double denominator;

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		if(denominator == 0) throw new IllegalArgumentException("denominator cannot be 0!");
		this.denominator = denominator;
		simplify();
	}

	public Fraction(int numerator) {
		this.numerator = numerator;
		denominator = 1;
	}

	public Fraction(Fraction f) {
		this.numerator = f.numerator;
		this.denominator = f.denominator;
	}

	public double getNumerator() {
		return numerator;
	}

	public double getDenominator() {
		return denominator;
	}

	public void mul(Fraction f) {
		this.numerator *= f.numerator;
		this.denominator *= f.denominator;
		simplify();
	}

	public void scale(double d) {
		this.numerator *= d;
		this.denominator *= d;
	}

	public void div(Fraction f) {
		this.numerator *= f.denominator;
		this.denominator *= f.numerator;
		simplify();
	}

	public void simplify() {
		this.scale(1/(double)gcd((int)Math.round(this.numerator), (int)Math.round(this.denominator)));
	}

	public static int gcd(int p, int q) {
		if (q == 0)
			return p;
		else
			return gcd(q, p%q);
	}

	public void add(Fraction f) {
		double d = this.denominator;
		this.scale(f.denominator);
		this.numerator += f.numerator*d;
		simplify();
	}

	public void sub(Fraction f) {
		double d = this.denominator;
		this.scale(f.denominator);
		this.numerator -= f.numerator*d;
		simplify();
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	public static void testAndPrint() {
		Fraction f = new Fraction(3, 7);
		System.out.println("Fraction: " + f);

		System.out.println("\nAdding 1/5");
		f.add(new Fraction(1, 5));
		System.out.println("Result should be 22/35");
		System.out.println("is: " + f);

		System.out.println("\nDividing by 6/23");
		f.div(new Fraction(6, 23));
		System.out.println("Result should be 253/105");
		System.out.println("is: " + f);

		System.out.println("\nSubtracting 87/210");
		f.sub(new Fraction(87, 210));
		System.out.println("Result should be: 419/210");
		System.out.println("is: " + f);

		System.out.println("\nMultiplying by 2/5");
		f.mul(new Fraction(2, 5));
		System.out.println("Result should be 419/525");
		System.out.println("is: " + f);

		System.out.println("Done!");
	}
}
