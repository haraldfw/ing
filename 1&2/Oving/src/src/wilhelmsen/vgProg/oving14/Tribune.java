package src.wilhelmsen.vgProg.oving14;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public abstract class Tribune implements Comparable, Serializable {

	private final String tribunenavn;
	private final int kapasitet;
	private final int pris;

	public Tribune(String tribunenavn, int kapasitet, int pris) {
		this.tribunenavn = tribunenavn;
		this.kapasitet = kapasitet;
		this.pris = pris;
	}

	public double finnInntekt() {
		return finnAntallSolgteBilletter()*pris;
	}

	public int getKapasitet() {
		return kapasitet;
	}

	public String getTribunenavn() {
		return tribunenavn;
	}

	public int getPris() {
		return pris;
	}


	public abstract int finnAntallSolgteBilletter();
	public abstract Billett[] kjoepBilletter(int amount);
	public abstract Billett[] kjoepBilletter(String[] names);

	@Override
	public String toString() {
		return "Tribune: " + getTribunenavn() + "\n\tKapasitet: " + getKapasitet() + "\n\tSolgte billetter: " + finnAntallSolgteBilletter()
					   + "\n\tInntekt: " + finnInntekt();
	}

	@Override
	public int compareTo(Object o) {
		return (int)Math.signum(this.finnInntekt() - ((Tribune)o).finnInntekt());
	}
}
