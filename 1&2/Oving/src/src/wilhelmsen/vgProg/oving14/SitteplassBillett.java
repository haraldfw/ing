package src.wilhelmsen.vgProg.oving14;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */


/**
 * Sitteplassbilletter. Rad og plass på raden må oppgis.
 */
public class SitteplassBillett extends Billett {
	private final int rad;
	private final int plass;

	public SitteplassBillett(String tribunenavn, int pris, int rad, int plass) {
		super(tribunenavn, pris);
		if (rad < 0 || plass < 0) {
			throw new IllegalArgumentException("Verken rad eller plass kan være negativ.\n"
													   + "Oppgitte verdier: " + rad + ", " + plass);
		}
		this.rad = rad;
		this.plass = plass;
	}

	public int getRad() {
		return rad;
	}

	public int getPlass() {
		return plass;
	}

	public String toString() {
		String res = super.toString();
		res += " Rad: "+ rad + " Plass: " + plass;
		return res;
	}
}
