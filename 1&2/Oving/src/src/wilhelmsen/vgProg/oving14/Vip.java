package src.wilhelmsen.vgProg.oving14;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public class Vip extends Tribune {
	private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

	public Vip(int rader, int radBredde, int prisPerBillett) {
		super("VIP", rader*radBredde, prisPerBillett);
		tilskuer = new String[rader][radBredde];
	}

	public boolean kjoepBillett(int plassX, int plassY, String navn) {
		if(tilskuer[plassX][plassY] != null) return false;
		tilskuer[plassX][plassY] = navn;
		return true;
	}

	@Override
	public int finnAntallSolgteBilletter() {
		int b = 0;
		for(int r = 0; r < tilskuer.length; r++) {
			for(int p = 0; p < tilskuer[r].length; p++) {
				if(tilskuer[r][p] != null) b++;
			}
		}
		return b;
	}

	@Override
	public Billett[] kjoepBilletter(String[] names) {
		// loop gjennom hver rad
		for(int r = 0; r < tilskuer.length; r++) {
			// loop gjennom hver plass på denne raden
			for(int p = 0; p < tilskuer[r].length; p++) {
				// hvis det er plass på raden
				if(tilskuer[r][p] == null && tilskuer[r].length - p >= names.length) {
					Billett[] b = new Billett[names.length];
					for(int i = 0; i < names.length; i++) {
						b[i] = new VipBillett(getTribunenavn(), getPris(), r, i + p, names[i]);
						tilskuer[r][p] = names[i];
					}
					return b;
				}
			}

		}
		return null;
	}

	@Override
	public Billett[] kjoepBilletter(int amount) {
		return null;
	}

	@Override
	public double finnInntekt() {
		return finnAntallSolgteBilletter()*getPris();
	}
}
